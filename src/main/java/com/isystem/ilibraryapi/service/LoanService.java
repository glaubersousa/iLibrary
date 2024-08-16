package com.isystem.ilibraryapi.service;

import com.isystem.ilibraryapi.enums.Status;
import com.isystem.ilibraryapi.model.Loan;
import com.isystem.ilibraryapi.model.User;
import com.isystem.ilibraryapi.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getByStatus(Status status) {
        return loanRepository.findByStatus(status);
    }

    public Loan register(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan update(Loan loan) {
        return loanRepository.save(loan);
    }

    public void delete(Long id) {
        loanRepository.deleteById(id);
    }
}
