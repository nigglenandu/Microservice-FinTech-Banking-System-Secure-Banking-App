package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Loan Management", description = "APIs for managing loan applications and repayments")
public class LoanController {
    private final IServiceLoan serviceLoan;

    public LoanController(IServiceLoan serviceLoan) {
        this.serviceLoan = serviceLoan;
    }

    @PostMapping("/apply")
    @Operation(summary = "Apply for a loan", description = "Submits a loan application for a user with the specified amount.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan application submitted successfully",
                    content = @Content(schema = @Schema(implementation = Loan.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user ID or amount", content = @Content)
    })
    public ResponseEntity<Loan> applyForLoan(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(serviceLoan.applyForLoan(userId, amount));
    }

    @Operation(summary = "Approve a loan", description = "Approves a loan identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan approved successfully",
                    content = @Content(schema = @Schema(implementation = Loan.class))),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)
    })
    @PutMapping("/approve/{loanId}")
    public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId) {
        return serviceLoan.approveLoan(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Reject a loan", description = "Rejects a loan identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan rejected successfully",
                    content = @Content(schema = @Schema(implementation = Loan.class))),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)
    })
    @PutMapping("/reject/{loanId}")
    public ResponseEntity<Loan> rejectLoan(@PathVariable Long loanId) {
        return serviceLoan.rejectLoan(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/repay/{loanId}")
    @Operation(summary = "Repay a loan", description = "Processes a repayment for a loan identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan repayment processed successfully",
                    content = @Content(schema = @Schema(implementation = Loan.class))),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)
    })
    public ResponseEntity<Loan> repayLoan(@PathVariable Long loanId) {
        return serviceLoan.repayLoan(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get loans by user", description = "Retrieves all loans for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of loans retrieved",
                    content = @Content(schema = @Schema(implementation = Loan.class, type = "array"))),
            @ApiResponse(responseCode = "404", description = "No loans found for the user", content = @Content)
    })
    public ResponseEntity<List<Loan>> getLoanByUser(@PathVariable Long userId) {
        return serviceLoan.getLoansByUser(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{loanId}/schedule")
    @Operation(summary = "Get repayment schedule", description = "Retrieves the repayment schedule for a loan identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Repayment schedule retrieved",
                    content = @Content(schema = @Schema(implementation = LoanRepaymentSchedule.class, type = "array"))),
            @ApiResponse(responseCode = "404", description = "No repayment schedule found", content = @Content)
    })
    public ResponseEntity<List<LoanRepaymentSchedule>> getRepaymentSchedule(@PathVariable Long loanId) {
        List<LoanRepaymentSchedule> schedules = ((LoanServiceImpl) serviceLoan).getRepaymentSchedules(loanId);
        return schedules.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(schedules);
    }

    @ExceptionHandler(Exception.class)
    @Operation(summary = "Handle general exceptions", description = "Catches and handles general exceptions during request processing.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing request: " + e.getMessage());
    }

    @ExceptionHandler(TypeMismatchException.class)
    @Operation(summary = "Handle type mismatch exceptions", description = "Catches and handles type mismatch errors for invalid parameters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid parameter",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> handleTypeMismatch(TypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid parameter: " + e.getMessage());
    }

}