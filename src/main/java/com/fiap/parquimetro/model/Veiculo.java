package com.fiap.parquimetro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Veiculo {

    @Id
    private String id;

    private String placa;

    private String marca;

    private String modelo;

    private Integer tipo;

    private String cor;
}
