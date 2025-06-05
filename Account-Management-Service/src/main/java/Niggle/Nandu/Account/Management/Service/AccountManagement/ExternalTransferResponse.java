package Niggle.Nandu.Account.Management.Service.AccountManagement;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object for external transfer operations")
public class ExternalTransferResponse {
    @Schema(description = "Indicates if the transfer was successful", example = "true")
    private boolean success;

    @Schema(description = "Message describing the transfer outcome", example = "Transfer completed successfully")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
