package com.fiap.parquimetro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OpcaoPagamento {

    @Id
    private String id;

    private String opcao;

    private Integer status;
}
