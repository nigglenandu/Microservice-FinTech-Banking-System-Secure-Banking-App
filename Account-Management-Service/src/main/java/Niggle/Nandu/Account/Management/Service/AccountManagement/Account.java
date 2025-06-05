package Niggle.Nandu.Account.Management.Service.AccountManagement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Description;

import javax.management.Descriptor;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the account", example = "1")
    private long id;

    @Column(name = "account_number")
    @NotNull
    @Schema(description = "Unique account number", example = "USER_ACC_1")
    private String accountNumber;

    @Schema(description = "Current balance of the account",  example = "1000.00")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Status of the account", example = "ACTIVE")
    private AccountStatus status;

    public Account( String accountNumber, BigDecimal balance, long id, AccountStatus status) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.id = id;
        this.status = status;
    }

    public Account() {
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
