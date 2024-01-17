package com.fiap.parquimetro.repository;

import com.fiap.parquimetro.model.Recibo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReciboRepository extends MongoRepository<Recibo, String> {

    public List<Recibo> findByCondutorId(String id);
}
