package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OpcaoPagamento {

    @Id
    private String id;

    private String opcao;

    private Status status;
}
