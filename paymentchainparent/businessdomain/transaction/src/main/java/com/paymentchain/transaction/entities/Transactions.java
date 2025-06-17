package com.paymentchain.transaction.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;
    private String accountIban;
    private Date date;
    private double amount ;
    private double fee;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    private Channel channel;


}
