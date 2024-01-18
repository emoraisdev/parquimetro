package com.fiap.parquimetro.repository;

import com.fiap.parquimetro.model.Condutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondutorRepository extends MongoRepository<Condutor, String> {
    boolean existsByEmail(String email);
}