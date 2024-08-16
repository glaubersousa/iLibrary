package com.isystem.ilibraryapi.service;

import com.isystem.ilibraryapi.model.LoanHistory;
import com.isystem.ilibraryapi.repository.LoanHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanHistoryService {

    private final LoanHistoryRepository repository;

    public LoanHistoryService(LoanHistoryRepository repository) {
        this.repository = repository;
    }

    public List<LoanHistory> getAll() {
        return repository.findAll();
    }

    public LoanHistory register(LoanHistory loanHistory) {
        return repository.save(loanHistory);
    }

    public LoanHistory update(LoanHistory loanHistory) {
        return repository.save(loanHistory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
