package com.fiap.parquimetro.service;

import com.fiap.parquimetro.dto.VeiculoDTO;

import java.util.List;

public interface VeiculoService {

    public VeiculoDTO criar(VeiculoDTO dto);

    public VeiculoDTO get(String id);

    public List<VeiculoDTO> getByCondutorId(String id);

    public void update(VeiculoDTO dto);
}
