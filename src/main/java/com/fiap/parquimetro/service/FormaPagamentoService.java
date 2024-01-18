package com.fiap.parquimetro.service;

import com.fiap.parquimetro.dto.FormaPagamentoDTO;
import com.fiap.parquimetro.dto.VeiculoDTO;
import com.fiap.parquimetro.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoService {

    FormaPagamentoDTO toDTO(FormaPagamento formaPagamento);
    FormaPagamento toEntity(FormaPagamentoDTO dto);
}
