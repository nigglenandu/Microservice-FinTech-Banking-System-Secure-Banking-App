package Niggle.Nandu.Account.Management.Service.AccountManagement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account Management", description = "APIs for managing accounts and fund transfers")
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final IServiceAccount serviceAccount;

    public AccountController(IServiceAccount serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new account", description = "Create a new account with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "Invalid account data", content = @Content)
    })
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account){
        return new ResponseEntity<>(serviceAccount.addAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all accounts", description = "Retrieves a list of all accounts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of accounts retrieved",
            content = @Content(schema = @Schema(implementation = Account.class,
            type = "array ")))
    })
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(serviceAccount.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID", description = "Fetches an account by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200",  description= "Account found",
            content= @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "Account not found", content = @Content)
    })
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){
        return serviceAccount.getAccountById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("by-number/{accountNumber}")
    @Operation(summary = "Get account by account number", description = "Fetches an account by its unique account number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found",
            content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "Account not found", content = @Content)
    })
    public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber){
        log.info("Fetching account with number: {}", accountNumber);
        return serviceAccount.getAccountByNumber(accountNumber)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account by ID", description = "Updates an existing account identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated successfully",
                    content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid account data", content = @Content)
    })
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody Account account) {
        return serviceAccount.updateAccountById(id, account)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/funds/transfer")
    @Operation(summary = "Transfer funds", description = "Initiates a fund transfer between two accounts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer initiated successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid transfer request", content = @Content)
    })
    public Optional<String> triggerFundTransfer(@RequestBody FundTransferRequestDto request) {
        log.info("Received transfer request: {}", request);
        if (request.getFromAccountNumber() == null) {
            log.error("fromAccountNumber is null in request: {}", request);
            return Optional.of("Transfer failed: Invalid fromAccountNumber");
        }
        if (request.getToAccountNumber() == null) {
            log.error("toAccountNumber is null in request: {}", request);
            return Optional.of("Transfer failed: Invalid toAccountNumber");
        }
        return serviceAccount.transferFunds(request);
    }
    
    @GetMapping("/test")
    @Operation(summary = "Test service", description = "Checks if the account service is running.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service is running",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Account Service is running");
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Delete account by ID", description = "Deletes an account identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        if(serviceAccount.deleteAccountById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
