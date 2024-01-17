package com.fiap.parquimetro.service;

import com.fiap.parquimetro.dto.ReciboDTO;

import java.util.List;

public interface ReciboService {

    public ReciboDTO get(String id);

    public List<ReciboDTO> getByCondutorId(String id);

}
