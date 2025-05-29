package Niggle.Nandu.Account.Management.Service.AccountManagement;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final IServiceAccount serviceAccount;

    public AccountController(IServiceAccount serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account){
        return new ResponseEntity<>(serviceAccount.addAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(serviceAccount.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){
        return serviceAccount.getAccountById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("by-number/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber){
        log.info("Fetching account with number: {}", accountNumber);
        return serviceAccount.getAccountByNumber(accountNumber)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody Account account) {
        return serviceAccount.updateAccountById(id, account)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/funds/transfer")
    public Optional<String> triggerFundTransfer(@RequestBody FundTransferRequestDto request) {
        return serviceAccount.transferFunds(request);
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Account Service is running");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        if(serviceAccount.deleteAccountById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
