package Niggle.Nandu.Fund.Transfer.Service.Dto;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class AccountDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_number")
    private String accountNumber;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    public AccountDto(String accountNumber, BigDecimal balance, long id, AccountStatus status) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.id = id;
        this.status = status;
    }

    public AccountDto() {
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

