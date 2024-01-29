package com.fiap.parquimetro.service;

import com.fiap.parquimetro.dto.LocalVagaDTO;
import com.fiap.parquimetro.model.Permanencia;

import java.math.BigDecimal;

public interface LocalVagaService {
    LocalVagaDTO buscarLocalVaga(String id);

    BigDecimal calcularValorEstacionamento(LocalVagaDTO localVagaDTO, Permanencia permanencia);

    LocalVagaDTO salvarVaga(LocalVagaDTO localVagaDTO);

    LocalVagaDTO update(String id, LocalVagaDTO localVagaDTO);

    public void deleteById(String id);
}
