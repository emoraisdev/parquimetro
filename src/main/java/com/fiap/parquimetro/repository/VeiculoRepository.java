package com.fiap.parquimetro.repository;

import com.fiap.parquimetro.model.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
}
