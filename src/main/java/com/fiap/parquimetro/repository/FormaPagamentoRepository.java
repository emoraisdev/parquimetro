package com.fiap.parquimetro.repository;

import com.fiap.parquimetro.model.FormaPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormaPagamentoRepository extends MongoRepository<FormaPagamento, String> {
    }
