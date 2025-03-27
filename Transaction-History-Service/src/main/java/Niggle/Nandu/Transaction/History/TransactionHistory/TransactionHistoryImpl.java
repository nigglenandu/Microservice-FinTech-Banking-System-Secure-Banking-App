package Niggle.Nandu.Transaction.History.TransactionHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryImpl implements IServiceTransaction{
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Page<Transaction> getTransactionHistory(Long accountId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        return transactionRepository.findByAccountId(accountId, pageable);
    }

    @Override
    public Page<Transaction> searchTransactionByType(Long accountId, String type, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        return transactionRepository.findByAccountIdAndType(accountId, type, pageable);
    }
}
