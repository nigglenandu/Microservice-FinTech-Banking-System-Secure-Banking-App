package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import Niggle.Nandu.Loan.Transfer.Service.Clients.AccountClient;
import Niggle.Nandu.Loan.Transfer.Service.Clients.FundTransferClient;
import Niggle.Nandu.Loan.Transfer.Service.Dto.AccountDto;
import Niggle.Nandu.Loan.Transfer.Service.Dto.AccountStatus;
import Niggle.Nandu.Loan.Transfer.Service.Dto.FundTransferRequestDto;
import Niggle.Nandu.Loan.Transfer.Service.Messaging.LoanNotificationProducer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements IServiceLoan {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private FundTransferClient fundTransferClient;

    @Autowired
    private LoanNotificationProducer notificationProducer;

    private static final String LOAN_SERVICE = "loanServiceBreaker";
    private static final String BANK_RESERVE_ACCOUNT = "BANK_RESERVE_001";
    @Autowired
    private LoanRepaymentScheduleRepo loanRepaymentScheduleRepo;

    @Override
    public Loan applyForLoan(Long userId, BigDecimal amount) {
        Integer creditScore = fetchCreditScore(userId);
        if (creditScore < 600) {
            notificationProducer.sendNotification("Loan rejected for user " + userId + ": Low credit score");
            throw new RuntimeException("Loan rejected: Credit score too low");
        }

        String accountNumber = fetchUserAccountNumber(userId);
        ResponseEntity<AccountDto> accountResponse = accountClient.getAccountByNumber(accountNumber);
        if (accountResponse.getStatusCode() != HttpStatus.OK || accountResponse.getBody() == null ||
                !accountResponse.getBody().getStatus().equals(AccountStatus.ACTIVE)) {
            notificationProducer.sendNotification("Loan rejected for user " + userId + ": Inactive account");
            throw new RuntimeException("Loan rejected: User account is not active");
        }

        Loan loan = new Loan(null, userId, amount, new BigDecimal("5.0"), "PENDING", LocalDate.now(), null, creditScore);
        loan.setRepaymentSchedule(generateRepaymentSchedule(loan));
        loan = loanRepository.save(loan);

        notificationProducer.sendNotification("Loan application submitted: ID " + loan.getId());
        return loan;
    }

    @Override
    @CircuitBreaker(name = LOAN_SERVICE, fallbackMethod = "approveLoanFallback")
    @Retry(name = LOAN_SERVICE)
    @Transactional
    public Optional<Loan> approveLoan(Long loanId) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isEmpty()) {
            return Optional.empty();
        }
        Loan loan = loanOpt.get();
        loan.setStatus("APPROVED");
        loan.setApprovalDate(LocalDate.now());
        loan = loanRepository.save(loan);

        try {
            disburseLoanAmount(loan);
            notificationProducer.sendNotification("Loan " + loan.getId() + " approved and disbursed for user " + loan.getUserId());
        } catch (Exception e) {
            notificationProducer.sendNotification("Loan approved but disbursement failed for loan " + loan.getId() + ": " + e.getMessage());
        }
        return Optional.of(loan);
    }

    @Override
    public Optional<Loan> rejectLoan(Long loanId) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    loan.setStatus("REJECTED");
                    loan = loanRepository.save(loan);
                    notificationProducer.sendNotification("Loan " + loan.getId() + " rejected");
                    return loan;
                });
    }

    @Override
    @CircuitBreaker(name = LOAN_SERVICE, fallbackMethod = "repayLoanFallback")
    @Retry(name = LOAN_SERVICE)
    public Optional<Loan> repayLoan(Long loanId) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    if (!"APPROVED".equals(loan.getStatus())) {
                        throw new RuntimeException("Loan is not approved");
                    }

                    List<LoanRepaymentSchedule> schedules = loanRepaymentScheduleRepo.findByLoanId(loanId);
                    LoanRepaymentSchedule nextPayment = schedules.stream()
                            .filter(s -> "PENDING".equals(s.getStatus()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No pending repayments"));

                    String userAccountNumber = fetchUserAccountNumber(loan.getUserId());
                    FundTransferRequestDto transferRequest = new FundTransferRequestDto(
                            nextPayment.getInstallmentAmount(),
                            userAccountNumber,
                            true,
                            BANK_RESERVE_ACCOUNT
                    );

                    ResponseEntity<String> transferResult = fundTransferClient.transferFunds(transferRequest);
                    if (transferResult.getStatusCode() != HttpStatus.OK || transferResult.getBody() == null ||
                            !transferResult.getBody().toLowerCase().contains("success")) {
                        notificationProducer.sendNotification("Repayment failed for loan " + loan.getId());
                        throw new RuntimeException("Repayment transfer failed");
                    }

                    nextPayment.setStatus("PAID");
                    loanRepository.save(loan);
                    notificationProducer.sendNotification("Repayment processed for loan " + loan.getId());

                    boolean allPaid = schedules.stream().allMatch(s -> "PAID".equals(s.getStatus()));
                    if (allPaid) {
                        loan.setStatus("REPAID");
                        loan = loanRepository.save(loan);
                        notificationProducer.sendNotification("Loan " + loan.getId() + " fully repaid");
                    }

                    return loan;
                });
    }

    @Override
    public Optional<List<Loan>> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId)
                .filter(loans -> !loans.isEmpty());
    }

    public List<LoanRepaymentSchedule> getRepaymentSchedules(Long loanId) {
        return loanRepaymentScheduleRepo.findByLoanId(loanId);
    }

    private List<LoanRepaymentSchedule> generateRepaymentSchedule(Loan loan) {
        List<LoanRepaymentSchedule> schedule = new ArrayList<>();
        BigDecimal monthlyInstallment = loan.getAmount()
                .multiply(loan.getInterestRate().divide(new BigDecimal("100")))
                .add(loan.getAmount())
                .divide(new BigDecimal("12"), 2, BigDecimal.ROUND_HALF_UP);

        for (int i = 1; i <= 12; i++) {
            schedule.add(new LoanRepaymentSchedule(
                    loan,
                    monthlyInstallment,
                    loan.getApplicationDate().plusMonths(i),
                    "PENDING"
            ));
        }
        return schedule;
    }

    private void disburseLoanAmount(Loan loan) {
        String userAccountNumber = fetchUserAccountNumber(loan.getUserId());
        FundTransferRequestDto transferRequest = new FundTransferRequestDto(
                loan.getAmount(),
                BANK_RESERVE_ACCOUNT,
                true,
                userAccountNumber
        );

        ResponseEntity<String> transferResult = fundTransferClient.transferFunds(transferRequest);
        if (transferResult.getStatusCode() != HttpStatus.OK || transferResult.getBody() == null ||
                !transferResult.getBody().toLowerCase().contains("success")) {
            notificationProducer.sendNotification("Loan disbursement failed for loan " + loan.getId());
            throw new RuntimeException("Loan disbursement failed");
        }
        notificationProducer.sendNotification("Loan amount disbursed for loan " + loan.getId());
    }

    private Integer fetchCreditScore(Long userId) {
        return 700; // Mocked
    }

    private String fetchUserAccountNumber(Long userId) {
        return "USER_ACC_" + userId; // Mocked
    }

    private Optional<Loan> approveLoanFallback(Long loanId, Throwable t) {
        notificationProducer.sendNotification("Loan approval failed for loan " + loanId + ": " + t.getMessage());
        return Optional.empty();
    }

    private Optional<Loan> repayLoanFallback(Long loanId, Throwable t) {
        notificationProducer.sendNotification("Loan repayment failed for loan " + loanId + ": " + t.getMessage());
        return Optional.empty();
    }
}