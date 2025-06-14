package com.paymentchain.transaction.repository;

import com.paymentchain.transaction.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {
}
