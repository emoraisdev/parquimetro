package com.fiap.parquimetro.model.enums;

import lombok.Getter;

@Getter
public enum PagamentoStatus {

    NAO_PAGO(0), PAGO(1);

    private int value;

    PagamentoStatus(int value){
        this.value = value;
    }

    public static PagamentoStatus fromValue(int value){
        for (PagamentoStatus status : PagamentoStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
