package com.upi.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.upi.tracker.entity.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}