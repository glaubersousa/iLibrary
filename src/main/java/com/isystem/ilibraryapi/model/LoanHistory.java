package com.isystem.ilibraryapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_loanhistory")
public class LoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "loanId")
    private Loan loan;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "bookId")
    private Book book;

    private LocalDate loanDate;

    private LocalDate returnDate;

    public LoanHistory() {

    }

    public LoanHistory(Loan loan, Book book, LocalDate loanDate, LocalDate returnDate) {
        this.loan = loan;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
