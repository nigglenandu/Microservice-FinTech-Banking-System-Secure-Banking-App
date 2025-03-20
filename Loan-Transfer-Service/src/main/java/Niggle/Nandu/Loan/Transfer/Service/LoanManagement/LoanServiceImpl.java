package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements IServiceLoan {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan applyForLoan(Long userId, BigDecimal amount) {
        Loan loan = new Loan(null, userId, amount, "PENDING", LocalDate.now(), null);
        return loanRepository.save(loan);
    }

    @Override
    public Optional<Loan> approveLoan(Long loanId) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    loan.setStatus("APPROVED");
                    loan.getApprovalDate(LocalDate.now());
                    return loanRepository.save(loan);
                });
    }

    @Override
    public Optional<Loan> rejectLoan(Long loanId) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    loan.setStatus("REJECTED");
                    return loanRepository.save(loan);
                });
    }

    @Override
    public Optional<Loan> repayLoan(Long loanId) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    if("APPROVED".equals(loan.getStatus())) {
                        return loanRepository.save(loan);
                    }
                    return loan;
                });
    }

    @Override
    public Optional<List<Loan>> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId)
                .filter(loans -> !loans.isEmpty());
    }
}
