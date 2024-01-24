package com.fiap.parquimetro.dto;


import com.fiap.parquimetro.model.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

public record CondutorDTO(
        String id,

        @NotNull(message = "O campo nome não pode ser nulo")
        String nome,
        LocalDate dataNascimento,

        String email,
        String telefone,

        @NotNull(message = "O status nome não pode ser nulo")
        Integer status,
        Endereco endereco,
        String idFormaPagamentoPreferida
) {
}
