package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
public class LocalVaga extends Endereco {

    @Id
    private String id;

    private BigDecimal valorHoraVariavel;

    private BigDecimal valorHoraFixa;

    private Status status;
}
