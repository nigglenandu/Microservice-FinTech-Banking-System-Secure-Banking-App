package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Schema(description = "Loan entity representing a loan application")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the loan", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "User ID associated with the loan", example = "1001")
    private Long userId;

    @NotNull
    @Positive
    @Schema(description = "Loan amount", example = "10000.00")
    private BigDecimal amount;

    @NotNull
    @Positive
    @Schema(description = "Interest rate for the loan", example = "5.5")
    private BigDecimal interestRate;

    @NotNull
    @Schema(description = "Status of the loan (e.g., PENDING, APPROVED, REJECTED, REPAID)", example = "PENDING")
    private String status;

    @NotNull
    @Schema(description = "Date the loan application was submitted", example = "2025-06-01")
    private LocalDate applicationDate;

    @Schema(description = "Date the loan was approved", example = "2025-06-05")
    private LocalDate approvalDate;

    @Schema(description = "Credit score of the user", example = "750")
    private Integer creditScore;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loan", fetch = FetchType.EAGER)
    @JsonManagedReference
    @Schema(description = "List of repayment schedules for the loan")
    private List<LoanRepaymentSchedule> repaymentSchedule;

    public Loan(Long id, Long userId, BigDecimal amount, BigDecimal interestRate, String status,
                LocalDate applicationDate, LocalDate approvalDate, Integer creditScore) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.status = status;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
        this.creditScore = creditScore;
    }


    public Loan() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public List<LoanRepaymentSchedule> getRepaymentSchedule() {
        return repaymentSchedule;
    }

    public void setRepaymentSchedule(List<LoanRepaymentSchedule> repaymentSchedule) {
        this.repaymentSchedule = repaymentSchedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
