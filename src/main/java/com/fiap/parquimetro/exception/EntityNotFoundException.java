package com.fiap.parquimetro.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String entidade) {
        super("Entidade %s não encontrada.".formatted(entidade));
    }
}
