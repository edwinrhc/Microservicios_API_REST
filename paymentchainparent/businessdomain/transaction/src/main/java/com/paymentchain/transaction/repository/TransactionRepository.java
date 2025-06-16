package com.paymentchain.transaction.repository;

import com.paymentchain.transaction.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {

    List<Transactions> findByAccountIban(String accountIban);
}
