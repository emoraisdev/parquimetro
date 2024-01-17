package com.fiap.parquimetro.dto;

import java.math.BigDecimal;
import java.time.Duration;

public record ReciboDTO(
        String id,
        BigDecimal valorHora,
        BigDecimal valorTotal,
        Duration tempoPermanencia,
        String idPermanencia,
        String idCondutor
) {
}
