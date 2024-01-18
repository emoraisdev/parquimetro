package com.fiap.parquimetro.dto;

import com.fiap.parquimetro.model.enums.TipoFormaPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDTO {
    private String id;
    private String descricao;
    private TipoFormaPagamento tipo;
    private String condutorId;
}