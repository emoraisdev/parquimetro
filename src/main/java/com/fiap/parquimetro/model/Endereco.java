package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
public class Endereco {

    @Id
    private String id;

    private String pais;

    private String estado;

    private String cep;

    private String cidade;

    private String bairro;

    private String rua;

    private Integer numero;

}