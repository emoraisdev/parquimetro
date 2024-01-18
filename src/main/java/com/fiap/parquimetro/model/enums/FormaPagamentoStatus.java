package com.fiap.parquimetro.model.enums;

import lombok.Getter;

@Getter
public enum FormaPagamentoStatus {

    NAO_PAGO(0), PAGO(1);

    private int value;

    FormaPagamentoStatus(int value){
        this.value = value;
    }

    public static FormaPagamentoStatus fromValue(int value){
        for (FormaPagamentoStatus status : FormaPagamentoStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
