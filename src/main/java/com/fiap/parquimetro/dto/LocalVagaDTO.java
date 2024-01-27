package com.fiap.parquimetro.dto;

import com.fiap.parquimetro.model.LocalVaga;
import com.fiap.parquimetro.model.Veiculo;
import com.fiap.parquimetro.model.enums.Status;

import java.math.BigDecimal;

public record LocalVagaDTO(
        String id,
        BigDecimal valorHoraVariavel,
        BigDecimal valorHoraFixa,
        Status status)
{
}
