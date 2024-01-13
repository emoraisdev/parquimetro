package com.fiap.parquimetro.model.enums;

import lombok.Getter;

@Getter
public enum NotificaoStatus {

    NAO_LIDA(0), LIDA(1);

    private int value;

    NotificaoStatus(int value){
        this.value = value;
    }

    public static NotificaoStatus fromValue(int value){
        for (NotificaoStatus status : NotificaoStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
