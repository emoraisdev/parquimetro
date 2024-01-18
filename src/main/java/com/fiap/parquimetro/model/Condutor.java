package com.fiap.parquimetro.model;


import com.fiap.parquimetro.model.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
public class Condutor {

    @Id
    private String id;

    private String nome;

    private LocalDate dataNascimento;

    private String email;

    private String telefone;

    private Status status;

    private FormaPagamento opcaoPagamentoPreferida;

    @DBRef
    private Endereco endereco;

    public Condutor(String id){
        this.id = id;
    }
}
