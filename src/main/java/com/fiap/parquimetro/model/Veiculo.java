package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Veiculo {

    @Id
    private String id;

    private String placa;

    private String marca;

    private String modelo;

    private TipoVeiculo tipo;

    private String cor;

    @DBRef
    private Condutor condutor;
}
