package com.fiap.parquimetro.dto;

import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.LocalVaga;
import com.fiap.parquimetro.model.Veiculo;
import com.fiap.parquimetro.model.enums.NotificaoStatus;
import com.fiap.parquimetro.model.enums.TipoFormaPagamento;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


public record NotificacaoDTO(
        String id,
        String mensagem,
        String idCondutor,
        LocalDateTime dateTime,
        Integer status

) {

}
