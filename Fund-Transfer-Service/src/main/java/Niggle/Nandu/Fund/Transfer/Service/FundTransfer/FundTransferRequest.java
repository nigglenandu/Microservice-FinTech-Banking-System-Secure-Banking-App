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

    @JsonProperty("fromAccount")
    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    @JsonProperty("fromAccount")
    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    @JsonProperty("toAccount")
    public String getToAccountNumber() {
        return toAccountNumber;
    }

    @JsonProperty("toAccount")
    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @JsonProperty("isExternalTransfer")
    public boolean isExternalTransfer() {
        return isExternalTransfer;
    }

    @JsonProperty("isExternalTransfer")
    public void setExternalTransfer(boolean externalTransfer) {
        this.isExternalTransfer = externalTransfer;
    }

    @JsonProperty("isCredit")
    public boolean isCredit() {
        return isCredit;
    }

    @JsonProperty("isCredit")
    public void setCredit(boolean credit) {
        this.isCredit = credit;
    }

    @Override
    public String toString() {
        return "FundTransferRequestDto{" +
                "fromAccountNumber='" + fromAccountNumber + '\'' +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", amount=" + amount +
                ", isExternalTransfer=" + isExternalTransfer +
                ", isCredit=" + isCredit +
                '}';
    }
}