package com.isystem.ilibraryapi.repository;

import com.isystem.ilibraryapi.enums.Status;
import com.isystem.ilibraryapi.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByStatus(Status status);


}
