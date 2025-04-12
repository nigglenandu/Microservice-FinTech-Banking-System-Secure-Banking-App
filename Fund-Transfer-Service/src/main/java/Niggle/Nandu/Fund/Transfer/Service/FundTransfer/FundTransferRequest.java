package Niggle.Nandu.Fund.Transfer.Service.FundTransfer;

import java.math.BigDecimal;

public class FundTransferRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;
    private boolean isExternalTransfer;

    public FundTransferRequest(BigDecimal amount, String fromAccountNumber, boolean isExternalTransfer, String toAccountNumber) {
        this.amount = amount;
        this.fromAccountNumber = fromAccountNumber;
        this.isExternalTransfer = isExternalTransfer;
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
