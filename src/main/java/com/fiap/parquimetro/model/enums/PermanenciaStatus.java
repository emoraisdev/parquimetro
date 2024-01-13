package com.fiap.parquimetro.model.enums;

import lombok.Getter;

@Getter
public enum PermanenciaStatus {

    EM_ANDAMENTO(0), FINALIZADA(1);

    private int value;

    PermanenciaStatus(int value){
        this.value = value;
    }

    public static TipoVeiculo fromValue(int value){
        for (TipoVeiculo status : TipoVeiculo.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
