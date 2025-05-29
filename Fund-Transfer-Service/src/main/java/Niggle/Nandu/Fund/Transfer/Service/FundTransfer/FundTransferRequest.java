package Niggle.Nandu.Fund.Transfer.Service.FundTransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class FundTransferRequest {
    @JsonProperty("fromAccount")
    private String fromAccountNumber;

    @JsonProperty("toAccount")
    private String toAccountNumber;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("isExternalTransfer")
    private boolean isExternalTransfer;

    @JsonProperty("isCredit")
    private boolean isCredit;

    public FundTransferRequest() {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isExternalTransfer() {
        return isExternalTransfer;
    }

    public void setExternalTransfer(boolean externalTransfer) {
        this.isExternalTransfer = externalTransfer;
    }

    public boolean isCredit() {
        return isCredit;
    }

    public void setCredit(boolean credit) {
        this.isCredit = credit;
    }

    @Override
    public String toString() {
        return "FundTransferRequest{" +
                "fromAccountNumber='" + fromAccountNumber + '\'' +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", amount=" + amount +
                ", isExternalTransfer=" + isExternalTransfer +
                ", isCredit=" + isCredit +
                '}';
    }
}