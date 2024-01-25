package com.fiap.parquimetro.dto;

public record EnderecoDTO(
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String pais,
        String cep
) {
}
