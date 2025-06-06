package Niggle.Nandu.Fund.Transfer.Service.FundTransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Request object for fund transfer between accounts")
public class FundTransferRequest {
    @JsonProperty("fromAccount")
    @NotNull
    @Schema(description = "Account number to transfer funds from", example = "USER_ACC_1")
    private String fromAccountNumber;

    @JsonProperty("toAccount")
    @NotNull
    @Schema(description = "Account number to transfer funds to", example = "USER_ACC_2")
    private String toAccountNumber;

    @JsonProperty("amount")
    @NotNull
    @Positive
    @Schema(description = "Amount to transfer", example = "100.00")
    private BigDecimal amount;

    @JsonProperty("isExternalTransfer")
    @Schema(description = "Indicates if the transfer is external", example = "false")
    private boolean isExternalTransfer;

    @JsonProperty("isCredit")
    @Schema(description = "Indicates if the transfer is a credit transaction", example = "false")
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