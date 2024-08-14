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


}
