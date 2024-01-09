package com.fiap.parquimetro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Notificacao {

    @Id
    private String id;

    private String mensagem;

    private Condutor condutor;

    private LocalDateTime dateTime;

    private Integer status;
}
