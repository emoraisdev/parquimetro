package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
@NoArgsConstructor
public class LocalVaga extends Endereco {

    private BigDecimal valorHoraVariavel;

    private BigDecimal valorHoraFixa;

    private Status status;

    public LocalVaga(String id){
        this.id = id;
    }
}
