package Niggle.Nandu.Account.Management.Service.AccountManagement;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

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

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody Account account) {
        return serviceAccount.updateAccountById(id, account)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
