package com.paymentchain.transaction.controllers;

import com.paymentchain.transaction.dto.TransactionDTO;
import com.paymentchain.transaction.entities.Transactions;
import com.paymentchain.transaction.repository.TransactionRepository;
import com.paymentchain.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
@Slf4j
public class TransactionRestController {

    private final TransactionService transactionService;

    @PostMapping
    public Transactions create(@Valid @RequestBody TransactionDTO dto){
        log.info("Creating transaction: {}", dto);
        log.info("Status recibido: " + dto.getStatus());
        Transactions tx = transactionService.fromDTO(dto);
        return transactionService.createTransaction(tx);
    }

    @PutMapping("/{id}")
    public Transactions update(@PathVariable("id") Long id,@Valid @RequestBody TransactionDTO dto){
        Transactions tx = transactionService.fromDTO(dto);
        return transactionService.updateTransaction(id, tx);
    }

    @GetMapping
    public List<Transactions> getAll(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/by-account/{iban}")
    public List<Transactions> getByAccount(@PathVariable("iban") String iban){
        return transactionService.getTransactionsByAccount(iban);
    }

    @GetMapping("/customer/transactions")
    public List<Transactions> get(@RequestParam(name="ibanAccount") String ibanAccount){
        List<Transactions> transactions = transactionService.getByAccount(ibanAccount);

        if(transactions.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontraron transacciones para el cliente con el Iban: " + ibanAccount);
        }

        return transactionService.getByAccount(ibanAccount);
    }





}
