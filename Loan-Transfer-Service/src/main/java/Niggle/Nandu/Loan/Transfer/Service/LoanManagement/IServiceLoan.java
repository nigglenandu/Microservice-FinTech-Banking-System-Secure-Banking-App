package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IServiceLoan {
    Loan applyForLoan(Long userId, BigDecimal amount);
    Optional<Loan> approveLoan(Long loanId);
    Optional<Loan> rejectLoan(Long loanId);
    Optional<Loan> repayLoan(Long loanId);
    Optional<List<Loan>> getLoansByUser(Long userId);
}