package com.fiap.parquimetro.service.impl;

import com.fiap.parquimetro.dto.ReciboDTO;
import com.fiap.parquimetro.exception.EntityNotFoundException;
import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.Permanencia;
import com.fiap.parquimetro.model.Recibo;
import com.fiap.parquimetro.repository.ReciboRepository;
import com.fiap.parquimetro.service.ReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReciboServiceImpl implements ReciboService {

    @Autowired
    private ReciboRepository repo;

    @Override
    public ReciboDTO get(String id) {
        return toDTO(repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Recibo")));
    }

    @Override
    public List<ReciboDTO> getByCondutorId(String id) {
        return repo.findByCondutorId(id).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Recibo toEntity(ReciboDTO dto) {
        return new Recibo(
                dto.id(),
                dto.valorHora(),
                dto.valorTotal(),
                dto.tempoPermanencia(),
                new Permanencia(dto.idPermanencia()),
                new Condutor(dto.idCondutor())
        );
    }

    public ReciboDTO toDTO(Recibo recibo){
        return new ReciboDTO(
                recibo.getId(),
                recibo.getValorHora(),
                recibo.getValorTotal(),
                recibo.getTempoPermanencia(),
                "0",
                "0"
        );
    }
}
