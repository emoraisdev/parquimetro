package com.fiap.parquimetro.service;

import com.fiap.parquimetro.dto.PermanenciaDTO;
import com.fiap.parquimetro.dto.VeiculoDTO;

import java.util.List;

public interface PermanenciaService {

    public PermanenciaDTO criar(PermanenciaDTO dto);

    public PermanenciaDTO get(String id);

    public List<PermanenciaDTO> getByCondutorId(String id);

    public void update(PermanenciaDTO dto);
}
