package Niggle.Nandu.Account.Management.Service.AccountManagement;

import java.math.BigDecimal;

public class ExternalTransferRequest {
    private Long fromAccountNumber;
    private BigDecimal amount;
    private Long toAccountNumber;

    public ExternalTransferRequest(BigDecimal amount, Long fromAccountNumber, Long toAccountNumber) {
        this.amount = amount;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }
}
