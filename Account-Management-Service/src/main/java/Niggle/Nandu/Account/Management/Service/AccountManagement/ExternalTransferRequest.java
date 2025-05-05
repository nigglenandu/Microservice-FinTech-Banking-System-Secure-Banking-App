package Niggle.Nandu.Account.Management.Service.AccountManagement;

import java.math.BigDecimal;

public class ExternalTransferRequest {
    private String fromAccountNumber;
    private BigDecimal amount;
    private String toAccountNumber;

    public ExternalTransferRequest(BigDecimal amount, String fromAccountNumber, String toAccountNumber) {
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

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }
}
