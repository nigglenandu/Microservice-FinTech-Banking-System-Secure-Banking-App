package Niggle.Nandu.Transaction.History.TransactionHistory;

import Niggle.Nandu.Transaction.History.Dto.AccountDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private String type;
    private Double amount;
    private LocalDateTime timestamp;
    private String status;
    private String description;

    public Transaction(Long accountId, Double amount, String description, Long id, String status, LocalDateTime timestamp, String type) {
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
        this.id = id;
        this.status = status;
        this.timestamp = timestamp;
        this.type = type;
    }

    public Transaction() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
