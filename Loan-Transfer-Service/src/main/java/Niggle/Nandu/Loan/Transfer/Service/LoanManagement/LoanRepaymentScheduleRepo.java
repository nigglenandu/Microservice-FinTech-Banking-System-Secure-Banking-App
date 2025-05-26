package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepaymentScheduleRepo extends JpaRepository<LoanRepaymentSchedule, Long> {
    List<LoanRepaymentSchedule> findByLoanId(Long loanId);
}
