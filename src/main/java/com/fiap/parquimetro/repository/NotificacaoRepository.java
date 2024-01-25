package com.fiap.parquimetro.repository;

import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.Notificacao;
import com.fiap.parquimetro.model.Permanencia;
import com.fiap.parquimetro.model.enums.NotificaoStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends MongoRepository<Notificacao, String> {

    public List<Notificacao> findByStatus(NotificaoStatus status);
}