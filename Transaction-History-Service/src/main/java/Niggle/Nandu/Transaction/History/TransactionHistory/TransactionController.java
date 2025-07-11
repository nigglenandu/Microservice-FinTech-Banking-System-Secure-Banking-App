package Niggle.Nandu.Transaction.History.TransactionHistory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction History", description = "APIs for retrieving transaction history")
public class TransactionController {

    @Autowired
    private IServiceTransaction serviceTransaction;

    @GetMapping("/{accountId}")
    @Operation(summary = "Get transaction history", description = "Retrieves paginated transaction history for a specific account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction history retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Account not found or no transactions", content = @Content)
    })
    public ResponseEntity<Page<Transaction>> getTransactionHistory(
            @PathVariable @Parameter(description = "Account ID") Long accountId,
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number (0-based)") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Number of transactions per page") int size) {
        return ResponseEntity.ok(serviceTransaction.getTransactionHistory(accountId, page, size));
    }

    @GetMapping("/{accountId}/search")
    @Operation(summary = "Search transactions by type", description = "Retrieves paginated transactions of a specific type for an account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Account not found or no transactions of specified type", content = @Content)
    })
    public ResponseEntity<Page<Transaction>> searchTransactionByType(
            @PathVariable @Parameter(description = "Account ID") Long accountId,
            @RequestParam @Parameter(description = "Transaction type (e.g., DEPOSIT, WITHDRAWAL, TRANSFER)") String type,
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number (0-based)") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Number of transactions per page") int size) {
        return ResponseEntity.ok(serviceTransaction.searchTransactionByType(accountId, type, page, size));
    }
}