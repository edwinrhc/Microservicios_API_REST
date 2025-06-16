package com.paymentchain.transaction.serviceImpl;

import com.paymentchain.transaction.dto.TransactionDTO;
import com.paymentchain.transaction.entities.Status;
import com.paymentchain.transaction.entities.Transactions;
import com.paymentchain.transaction.repository.TransactionRepository;
import com.paymentchain.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TransactionsServiceImpl implements TransactionService {

    public final TransactionRepository transactionRepository;

    public TransactionsServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Transactions createTransaction(Transactions transaction) {

        validateTransaction(transaction);
        applyBusinessRules(transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transactions updateTransaction(Long id, Transactions transaction) {
        Transactions existing = transactionRepository
                            .findById(id)
                            .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transaction.setId(existing.getId());
        validateTransaction(transaction);
        applyBusinessRules(transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transactions> getTransactionsByAccount(String accountIban) {
        return transactionRepository.findByAccountIban(accountIban);
    }

    @Override
    public void validateTransaction(Transactions tx) {
        if(tx.getAmount() == 0){
            throw new IllegalArgumentException("El monto no puede ser 0");
        }
    }

    @Override
    public void applyBusinessRules(Transactions tx) {
        if (tx.getDate() == null) {
            throw new IllegalArgumentException("La fecha de la transacción no puede ser nula");
        }

        if(tx.getFee() > 0){
            tx.setAmount(tx.getAmount() - tx.getFee());
        }
        if(tx.getDate().after(new Date())){
            tx.setStatus(Status.PENDIENTE);
        }else{
            tx.setStatus(Status.LIQUIDADA);
        }
    }

    @Override
    public List<Transactions> getByAccount(String accountIban) {
        return transactionRepository.findByAccountIban(accountIban);
    }

    @Override
    public Transactions fromDTO(TransactionDTO dto) {
        Transactions tx = new Transactions();
        tx.setReference(dto.getReference());
        tx.setAccountIban(dto.getAccountIban());
        tx.setDate(dto.getDate());
        tx.setAmount(dto.getAmount());
        tx.setFee(dto.getFee());
        tx.setDescription(dto.getDescription());
        tx.setStatus(dto.getStatus());
        tx.setChannel(dto.getChannel());
        return tx;
    }
}
