package Niggle.Nandu.Transaction.History.TransactionHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByAccountId(Long accountId, PageRequest pageable);
    Page<Transaction> findByAccountIdAndType(Long accountId, String type, PageRequest pageable);
}
