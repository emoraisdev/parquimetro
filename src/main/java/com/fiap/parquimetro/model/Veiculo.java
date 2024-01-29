package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.Status;
import com.fiap.parquimetro.model.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    private String id;

    private String placa;

    private String marca;

    private String modelo;

    private TipoVeiculo tipo;

    private String cor;

    private Status status;

    @DBRef
    private Condutor condutor;

    public Veiculo(String id){
        this.id = id;
    }
}
