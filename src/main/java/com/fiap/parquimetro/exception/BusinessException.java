package com.fiap.parquimetro.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String msg){
        super(msg);
    }
}
