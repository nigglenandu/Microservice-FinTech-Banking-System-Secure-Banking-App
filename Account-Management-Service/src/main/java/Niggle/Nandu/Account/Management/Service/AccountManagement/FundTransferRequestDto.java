package Niggle.Nandu.Account.Management.Service.AccountManagement;

import java.math.BigDecimal;

public class FundTransferRequestDto {
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;
    private boolean isExternalTransfer;

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

    public boolean isExternalTransfer() {
        return isExternalTransfer;
    }

    public void setExternalTransfer(boolean externalTransfer) {
        isExternalTransfer = externalTransfer;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }
}
