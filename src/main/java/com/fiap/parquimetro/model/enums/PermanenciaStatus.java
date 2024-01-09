package com.fiap.parquimetro.model.enums;

public enum PermanenciaStatus {

    EM_ANDAMENTO(0), FINALIZADA(1);

    private int value;

    PermanenciaStatus(int value){
        this.value = value;
    }
}
