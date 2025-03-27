package Niggle.Nandu.Transaction.History.TransactionHistory;

import org.springframework.data.domain.Page;

public interface IServiceTransaction {
    Page<Transaction>  getTransactionHistory(Long accountId, int page, int size);
    Page<Transaction>  searchTransactionByType(Long accountId, String type, int page, int size);
 }
