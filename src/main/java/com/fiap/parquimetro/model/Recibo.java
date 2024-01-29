package com.fiap.parquimetro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Duration;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recibo {

    @Id
    private String id;

    private BigDecimal valorHora;

    private BigDecimal valorTotal;

    private Duration tempoPermanencia;

    @DBRef
    private Permanencia permanencia;

    @DBRef
    private Condutor condutor;
}
