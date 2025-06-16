package com.paymentchain.transaction.service;

import com.paymentchain.transaction.dto.TransactionDTO;
import com.paymentchain.transaction.entities.Transactions;

import java.util.List;

public interface TransactionService {

    Transactions createTransaction(Transactions transaction);
    Transactions updateTransaction(Long id, Transactions transaction);

    List<Transactions> getAllTransactions();
    List<Transactions> getTransactionsByAccount(String accountIban);
    void validateTransaction(Transactions tx);
    void applyBusinessRules(Transactions tx);

    Transactions fromDTO(TransactionDTO dto);

    List<Transactions> getByAccount(String accountIban);

}
