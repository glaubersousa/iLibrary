package com.isystem.ilibraryapi.repository;

import com.isystem.ilibraryapi.model.LoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long> {



}
