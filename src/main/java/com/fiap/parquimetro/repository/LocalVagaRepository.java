package com.fiap.parquimetro.repository;

import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.LocalVaga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalVagaRepository extends MongoRepository<LocalVaga, String> {

}