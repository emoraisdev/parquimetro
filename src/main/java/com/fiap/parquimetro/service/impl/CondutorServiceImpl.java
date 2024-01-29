package com.fiap.parquimetro.service.impl;

import com.fiap.parquimetro.dto.CondutorDTO;
import com.fiap.parquimetro.dto.EnderecoDTO;
import com.fiap.parquimetro.exception.EntityNotFoundException;
import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.Endereco;
import com.fiap.parquimetro.model.enums.Status;
import com.fiap.parquimetro.repository.CondutorRepository;
import com.fiap.parquimetro.repository.EnderecoRepository;
import com.fiap.parquimetro.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CondutorServiceImpl implements CondutorService {

    @Autowired
    private CondutorRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoServiceImpl enderecoService;

    @Override
    public CondutorDTO criar(CondutorDTO dto) {

        var condutorEndereco = dto.endereco();
        var condutor = toEntity(dto);

        enderecoRepository.save(condutorEndereco);
        return toDTO(repository.save(condutor));
    }

    @Override
    public CondutorDTO get(String id) {
        return toDTO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Condutor")));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void update(CondutorDTO condutorDTO) {
        this.get(condutorDTO.id());
        var condutorEndereco = condutorDTO.endereco();

        enderecoRepository.save(condutorEndereco);
        repository.save(toEntity(condutorDTO));
    }

    @Override
    public List<CondutorDTO> getCondutorList() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Condutor toEntity(CondutorDTO condutorDTO){
        return new Condutor(
                condutorDTO.id(),
                condutorDTO.nome(),
                condutorDTO.dataNascimento(),
                condutorDTO.email(),
                condutorDTO.telefone(),
                condutorDTO.status() != null ?  Status.fromValue(condutorDTO.status()) : null,
                condutorDTO.endereco(),
                condutorDTO.formaPagamentoPreferida()
        );
    }

    public CondutorDTO toDTO(Condutor condutor){
        return new CondutorDTO(
                condutor.getId(),
                condutor.getNome(),
                condutor.getDataNascimento(),
                condutor.getEmail(),
                condutor.getTelefone(),
                condutor.getStatus().getValue(),
                condutor.getEndereco(),
                condutor.getOpcaoPagamentoPreferida()
        );
    }
}
