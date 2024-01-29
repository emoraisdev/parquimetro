package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.PagamentoStatus;
import com.fiap.parquimetro.model.enums.PermanenciaStatus;
import com.fiap.parquimetro.model.enums.TipoFormaPagamento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permanencia {

    @Id
    private String id;

    @DBRef(lazy = true)
    private Condutor condutor;

    @DBRef(lazy = true)
    private Veiculo veiculo;

    @DBRef(lazy = true)
    private LocalVaga local;

    private LocalDateTime entrada;

    private LocalDateTime saida;

    private TipoFormaPagamento tipoPagamento;

    private PagamentoStatus pagamentoStatus;

    private PermanenciaStatus permanenciaStatus;

    private Boolean tempoFixo;

    private Integer horasTempoFixo;

    public Permanencia(String id){
        this.id = id;
    }
}
