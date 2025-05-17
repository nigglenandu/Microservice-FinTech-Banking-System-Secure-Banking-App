package Niggle.Nandu.Loan.Transfer.Service.LoanManagement;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class LoanRepaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    private BigDecimal installmentAmount;
    private LocalDate dueDate;
    private String status;

    public LoanRepaymentSchedule(LocalDate dueDate, Long id, BigDecimal installmentAmount, Loan loan, String status) {
        this.dueDate = dueDate;
        this.id = id;
        this.installmentAmount = installmentAmount;
        this.loan = loan;
        this.status = status;
    }

    public LoanRepaymentSchedule() {
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(BigDecimal installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
