package com.fiap.parquimetro.repository;

import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends MongoRepository<Endereco, String> {

}