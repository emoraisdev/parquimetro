package com.fiap.parquimetro.model.enums;

public enum TipoVeiculo {

    CARRO(0), MOTO(1), CAMINHAO(2);

    private int value;

    TipoVeiculo(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
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
