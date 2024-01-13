package com.fiap.parquimetro.service;

import com.fiap.parquimetro.dto.VeiculoDTO;

public interface VeiculoService {

    public VeiculoDTO criar(VeiculoDTO veiculoDTO);

    public VeiculoDTO get(String id);
}
