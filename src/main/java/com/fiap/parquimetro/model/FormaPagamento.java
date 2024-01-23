package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.Status;
import com.fiap.parquimetro.model.enums.TipoFormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "formaPagamento")
@Setter
@Getter
@Builder
@AllArgsConstructor
public class FormaPagamento {
    @Id
    private String id;
    private String descricao;
    private TipoFormaPagamento tipo;
    private String condutorId;
}
