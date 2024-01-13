package com.fiap.parquimetro.repository;

import com.fiap.parquimetro.model.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {

    public List<Veiculo> findByCondutorId(String id);
}
