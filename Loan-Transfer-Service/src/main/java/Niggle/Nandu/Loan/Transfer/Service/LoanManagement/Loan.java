package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private String status;
    private LocalDate applicationDate;
    private LocalDate approvalDate;
    private Integer creditScore;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loan", fetch = FetchType.EAGER)
    @JsonManagedReference
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
