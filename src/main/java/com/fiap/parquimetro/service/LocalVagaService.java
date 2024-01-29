package com.fiap.parquimetro.service;

import com.fiap.parquimetro.dto.LocalVagaDTO;
import com.fiap.parquimetro.model.Permanencia;

import java.math.BigDecimal;

public interface LocalVagaService {
    public LocalVagaDTO buscarLocalVaga(String id);

    public BigDecimal calcularValorEstacionamento(LocalVagaDTO localVagaDTO, Permanencia permanencia);

    public LocalVagaDTO salvarVaga(LocalVagaDTO localVagaDTO);

    public LocalVagaDTO update(String id, LocalVagaDTO localVagaDTO);

    public void deleteById(String id);
}
