package com.fiap.parquimetro.service;

import com.fiap.parquimetro.dto.CondutorDTO;
import com.fiap.parquimetro.dto.VeiculoDTO;

import java.util.List;

public interface CondutorService {

    public CondutorDTO criar(CondutorDTO dto);

    public CondutorDTO get(String id);

    public void update(CondutorDTO dto);

    public void deleteById(String id);

    public List<CondutorDTO> getCondutorList();
}
