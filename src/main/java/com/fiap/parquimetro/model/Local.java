package com.fiap.parquimetro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
public class Local {

    @Id
    private String id;

    private String pais;

    private String estado;

    private String cep;

    private String cidade;

    private String bairro;

    private String rua;

    private Integer numero;

    private BigDecimal valorHoraVariavel;

    private BigDecimal valorHoraFixa;
}