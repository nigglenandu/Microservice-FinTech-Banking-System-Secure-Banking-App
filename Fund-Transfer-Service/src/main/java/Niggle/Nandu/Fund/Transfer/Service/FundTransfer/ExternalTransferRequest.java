package Niggle.Nandu.Fund.Transfer.Service.FundTransfer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Request object for external fund transfers")public class ExternalTransferRequest {
    @NotNull
    @Schema(description = "Account number to transfer funds from", example = "USER_ACC_1")
    private String fromAccountNumber;

    @NotNull
    @Positive
    @Schema(description = "Amount to transfer", example = "100.00")
    private BigDecimal amount;

    @NotNull
    @Schema(description = "Account number to transfer funds to", example = "USER_ACC_2")
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
