package Niggle.Nandu.Loan.Transfer.Service.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class FundTransferRequestDto {
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


    public FundTransferRequestDto(BigDecimal amount, String fromAccountNumber, boolean isCredit, boolean isExternalTransfer, String toAccountNumber) {
        this.amount = amount;
        this.fromAccountNumber = fromAccountNumber;
        this.isCredit = isCredit;
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

    public boolean isCredit() {
        return isCredit;
    }

    public void setCredit(boolean credit) {
        isCredit = credit;
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
