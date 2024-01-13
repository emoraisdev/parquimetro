package com.fiap.parquimetro.service.impl;

import com.fiap.parquimetro.dto.VeiculoDTO;
import com.fiap.parquimetro.exception.BusinessException;
import com.fiap.parquimetro.exception.EntityNotFoundException;
import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.Veiculo;
import com.fiap.parquimetro.model.enums.TipoVeiculo;
import com.fiap.parquimetro.repository.VeiculoRepository;
import com.fiap.parquimetro.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository repo;

    @Override
    public VeiculoDTO criar(VeiculoDTO veiculoDTO) {

        return toDTO(repo.save(toEntity(veiculoDTO)));
    }

    @Override
    public VeiculoDTO get(String id) {
        return toDTO(repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo")));
    }

    public Veiculo toEntity(VeiculoDTO dto){
        return new Veiculo(
                dto.id(),
                dto.placa(),
                dto.marca(),
                dto.modelo(),
                TipoVeiculo.fromValue(dto.tipo()),
                dto.cor(),
                new Condutor(dto.idCondutor())
        );
    }

    public VeiculoDTO toDTO(Veiculo veiculo){
        return new VeiculoDTO(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getTipo().getValue(),
                veiculo.getCor(),
                "0"
        );
    }
}
