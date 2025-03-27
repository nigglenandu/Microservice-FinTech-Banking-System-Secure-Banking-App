package Niggle.Nandu.Transaction.History.TransactionHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private IServiceTransaction serviceTransaction;

    @GetMapping("/{accountId}")
    private ResponseEntity<Page<Transaction>> getTransactionHistory(@PathVariable Long accountId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(serviceTransaction.getTransactionHistory(accountId, page, size));
    }

    @GetMapping("/{accountId}/search")
    private ResponseEntity<Page<Transaction>> searchTransactionByType(@PathVariable Long accountId, @RequestParam String type, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(serviceTransaction.searchTransactionByType(accountId, type, page, size));
    }
}
