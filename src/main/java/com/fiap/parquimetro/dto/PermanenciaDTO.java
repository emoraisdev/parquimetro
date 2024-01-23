package com.fiap.parquimetro.dto;

import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.FormaPagamento;
import com.fiap.parquimetro.model.LocalVaga;
import com.fiap.parquimetro.model.Veiculo;
import com.fiap.parquimetro.model.enums.FormaPagamentoStatus;
import com.fiap.parquimetro.model.enums.PermanenciaStatus;
import com.fiap.parquimetro.model.enums.TipoFormaPagamento;

import java.time.LocalDateTime;

public record PermanenciaDTO(
        String id,
        Condutor condutor,
        Veiculo veiculo,
        LocalVaga local,
        LocalDateTime entrada,
        LocalDateTime saida,
        TipoFormaPagamento tipoPagamento,
        Integer statusPagamento,
        Integer status,
        Boolean tempoFixo,
        Integer horasTempoFixo

) {

}
