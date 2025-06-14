package com.paymentchain.transaction.entities;

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

    /**
     * Busca un EstadoPago por su código.
     * @param codigo el código a buscar
     * @return el EstadoPago correspondiente, o lanza IllegalArgumentException si no existe
     */
    public static Status fromCodigo(String codigo) {
        for (Status e : values()) {
            if (e.codigo.equals(codigo)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Código del Status inválido: " + codigo);
    }
}
