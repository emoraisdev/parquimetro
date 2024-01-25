package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.PagamentoStatus;
import com.fiap.parquimetro.model.enums.PermanenciaStatus;
import com.fiap.parquimetro.model.enums.TipoFormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Getter
@Setter
@AllArgsConstructor
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

    private TipoFormaPagamento tipoPagamento;

    private PagamentoStatus statusPagamento;

    private PermanenciaStatus status;

    private Boolean tempoFixo;

    private Integer horasTempoFixo;

    public Permanencia(String id){
        this.id = id;
    }
}
