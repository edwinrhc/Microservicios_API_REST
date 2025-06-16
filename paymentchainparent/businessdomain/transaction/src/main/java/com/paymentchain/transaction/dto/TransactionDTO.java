package com.paymentchain.transaction.dto;

import com.paymentchain.transaction.entities.Channel;
import com.paymentchain.transaction.entities.Status;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {

    @NotBlank(message = "El campo reference es obligatorio")
    private String reference;

    @NotBlank(message = "El campo Iban es obligatorio")
    private String accountIban;

    @NotNull(message = "El campo fecha es obligatorio")
    private Date date;

    @NotNull(message = "El campo monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private double amount;

    @DecimalMin(value = "0.0", message = "La comisión no puede ser negativa")
    private double fee;

    @Size(max = 255, message = "Descripción muy larga")
    private String description;

    @NotNull(message = "El estado es obligatorio")
    private Status status;

    @NotNull(message = "El canal es obligatorio")
    private Channel channel;


}
