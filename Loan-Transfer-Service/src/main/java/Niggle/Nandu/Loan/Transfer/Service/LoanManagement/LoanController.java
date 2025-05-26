package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final IServiceLoan serviceLoan;

    public LoanController(IServiceLoan serviceLoan) {
        this.serviceLoan = serviceLoan;
    }

    @PostMapping("/apply")
    public ResponseEntity<Loan> applyForLoan(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(serviceLoan.applyForLoan(userId, amount));
    }

    @PutMapping("/approve/{loanId}")
    public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId) {
        return serviceLoan.approveLoan(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/reject/{loanId}")
    public ResponseEntity<Loan> rejectLoan(@PathVariable Long loanId) {
        return serviceLoan.rejectLoan(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/repay/{loanId}")
    public ResponseEntity<Loan> repayLoan(@PathVariable Long loanId) {
        return serviceLoan.repayLoan(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoanByUser(@PathVariable Long userId) {
        return serviceLoan.getLoansByUser(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{loanId}/schedule")
    public ResponseEntity<List<LoanRepaymentSchedule>> getRepaymentSchedule(@PathVariable Long loanId) {
        List<LoanRepaymentSchedule> schedules = ((LoanServiceImpl) serviceLoan).getRepaymentSchedules(loanId);
        return schedules.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(schedules);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing request: " + e.getMessage());
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(TypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid parameter: " + e.getMessage());
    }

}