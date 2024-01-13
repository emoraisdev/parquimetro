package com.fiap.parquimetro.dto;

public record VeiculoDTO(
        String id,
        String placa,
        String marca,
        String modelo,
        Integer tipo,
        String cor,
        Integer status,
        String idCondutor
) {
}
