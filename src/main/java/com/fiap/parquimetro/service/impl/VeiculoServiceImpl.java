package com.fiap.parquimetro.service.impl;

import com.fiap.parquimetro.dto.VeiculoDTO;
import com.fiap.parquimetro.exception.BusinessException;
import com.fiap.parquimetro.exception.EntityNotFoundException;
import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.Veiculo;
import com.fiap.parquimetro.model.enums.Status;
import com.fiap.parquimetro.model.enums.TipoVeiculo;
import com.fiap.parquimetro.repository.VeiculoRepository;
import com.fiap.parquimetro.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository repo;

    @Override
    public VeiculoDTO criar(VeiculoDTO dto) {

        var veiculo = toEntity(dto);
        veiculo.setStatus(Status.ATIVO);

        return toDTO(repo.save(veiculo));
    }

    @Override
    public VeiculoDTO get(String id) {
        return toDTO(repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo")));
    }

    @Override
    public List<VeiculoDTO> getByCondutorId(String id) {
        return repo.findByCondutorId(id).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public void update(VeiculoDTO dto) {

        this.get(dto.id());
        repo.save(toEntity(dto));
    }

    public Veiculo toEntity(VeiculoDTO dto){
        return new Veiculo(
                dto.id(),
                dto.placa(),
                dto.marca(),
                dto.modelo(),
                TipoVeiculo.fromValue(dto.tipo()),
                dto.cor(),
                dto.status() != null ?  Status.fromValue(dto.status()) : null,
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
                veiculo.getStatus().getValue(),
                "0"
        );
    }
}
