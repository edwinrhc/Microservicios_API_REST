package com.paymentchain.transaction.controllers;

import com.paymentchain.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionRestController {

    @Autowired
    TransactionRepository transactionRepository;


}
