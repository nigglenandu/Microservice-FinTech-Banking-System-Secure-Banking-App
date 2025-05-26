package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<List<Loan>> findByUserId(Long userId);
}
