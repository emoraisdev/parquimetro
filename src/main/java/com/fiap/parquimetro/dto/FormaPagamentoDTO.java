package com.fiap.parquimetro.dto;

import com.fiap.parquimetro.model.enums.TipoFormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FormaPagamentoDTO {
    private String id;
    private String descricao;
    private TipoFormaPagamento tipo;
    private String condutorId;
}