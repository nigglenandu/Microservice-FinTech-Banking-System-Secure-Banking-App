package Niggle.Nandu.Fund.Transfer.Service.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Dto representing an account for fund transfer operations")
public class AccountDto {
    @Schema(description = "Unique identifier for the account", example = "1")
    private long id;

    @NotNull
    @Schema(description = "Unique account number", example = "USER_ACC_1")
    @Column(name = "account_number")
    private String accountNumber;

    @Schema(description = "Current balance of the account",  example = "1000.00")
    private BigDecimal balance;


    @Enumerated(EnumType.STRING)
    @Schema(description = "Status of the account", example = "ACTIVE")
    private AccountStatus status;

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

