package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private String status;
    private LocalDate applicationDate;
    private LocalDate approvalDate;
    private Integer creditScore;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loan")
    private List<LoanRepaymentSchedule> repaymentSchedule;

    public Loan(BigDecimal amount, LocalDate applicationDate, LocalDate approvalDate, Integer creditScore, Long id, BigDecimal interestRate, List<LoanRepaymentSchedule> repaymentSchedule, String status, Long userId) {
        this.amount = amount;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
        this.creditScore = creditScore;
        this.id = id;
        this.interestRate = interestRate;
        this.repaymentSchedule = repaymentSchedule;
        this.status = status;
        this.userId = userId;
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
