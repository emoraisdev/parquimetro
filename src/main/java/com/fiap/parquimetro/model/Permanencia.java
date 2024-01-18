package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.FormaPagamentoStatus;
import com.fiap.parquimetro.model.enums.PermanenciaStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Permanencia {

    @Id
    private String id;

    @DBRef
    private Condutor condutor;

    @DBRef
    private Veiculo veiculo;

    @DBRef
    private LocalVaga local;

    private LocalDateTime entrada;

    private LocalDateTime saida;

    @DBRef
    private FormaPagamento opcaoPagamento;

    private FormaPagamentoStatus statusPagamento;

    private PermanenciaStatus status;

    private Boolean tempoFixo;

    private Integer horasTempoFixo;

    public Permanencia(String id){
        this.id = id;
    }
}
