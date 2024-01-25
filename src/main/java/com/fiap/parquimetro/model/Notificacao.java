package com.fiap.parquimetro.model;

import com.fiap.parquimetro.model.enums.NotificaoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notificacao {

    @Id
    private String id;

    private String mensagem;

    @DBRef
    private Condutor condutor;

    private LocalDateTime dateTime;

    private NotificaoStatus status;
}
