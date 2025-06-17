package com.paymentchain.transaction.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Status {

    PENDIENTE("01","Pendiente"),
    LIQUIDADA("02","Liquidada"),
    RECHAZADA("03","Rechazada"),
    CANCELADA("04","Cancelada");

    private final String codigo;
    private final String descripcion;

    Status(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    @JsonCreator
    public static Status fromJson(String value) {
        for (Status s : values()) {
            if (s.name().equalsIgnoreCase(value) || s.codigo.equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + value);
    }

    @JsonValue
    public String toJson() {
        return this.name(); // o return this.codigo; si prefieres mostrar "01", "02", etc.
    }
}
