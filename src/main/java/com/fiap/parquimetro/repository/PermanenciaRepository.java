package com.fiap.parquimetro.repository;

import com.fiap.parquimetro.model.Permanencia;
import com.fiap.parquimetro.model.enums.PermanenciaStatus;
import com.fiap.parquimetro.model.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PermanenciaRepository extends MongoRepository<Permanencia, String> {

    public List<Permanencia> findByCondutorId(String id);

    public List<Permanencia> findByStatus(PermanenciaStatus status);
}
