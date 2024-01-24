package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.Status;
import com.fiap.parquimetro.model.enums.TipoFormaPagamento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "formaPagamento")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {
    @Id
    private String id;
    private String descricao;
    private TipoFormaPagamento tipo;
    private String condutorId;

    public FormaPagamento(String id){
        this.id = id;
    }
}
