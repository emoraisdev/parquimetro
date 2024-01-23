package com.fiap.parquimetro.service.impl;

import com.fiap.parquimetro.dto.FormaPagamentoDTO;
import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.FormaPagamento;
import com.fiap.parquimetro.repository.CondutorRepository;
import com.fiap.parquimetro.repository.FormaPagamentoRepository;
import com.fiap.parquimetro.service.FormaPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FormaPagamentoServiceImpl implements FormaPagamentoService {
    private final CondutorRepository condutorRepository;
    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamentoDTO criarFormaPagamento(FormaPagamentoDTO formaPagamentoDTO) {
        String condutorId = formaPagamentoDTO.getCondutorId();
        Optional<Condutor> condutorOpt = condutorRepository.findById(condutorId);
        if (condutorOpt.isEmpty()) {
            throw new DataIntegrityViolationException("Condutor com ID não encontrado: " + condutorId);
        }

        FormaPagamento novaFormaPagamento = formaPagamentoRepository.save(toEntity(formaPagamentoDTO));
        Condutor condutor = condutorOpt.get();

        if (condutor.getOpcaoPagamentoPreferida() == null) {
            condutor.setOpcaoPagamentoPreferida(novaFormaPagamento);
        }

        condutorRepository.save(condutor);
        return toDTO(novaFormaPagamento);
    }

    public Optional<FormaPagamento> obterFormaPagamento(String id) {
        return formaPagamentoRepository.findById(id);
    }

    public List<FormaPagamentoDTO> findAll() {
        return formaPagamentoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        formaPagamentoRepository.deleteAll();
    }

    @Override
    public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento) {
        return new FormaPagamentoDTO(
            formaPagamento.getId(),
            formaPagamento.getDescricao(),
            formaPagamento.getTipo(),
            formaPagamento.getCondutorId()
        );
    }

    @Override
    public FormaPagamento toEntity(FormaPagamentoDTO dto) {
        return new FormaPagamento(
                dto.getId(),
                dto.getDescricao(),
                dto.getTipo(),
                dto.getCondutorId()
        );
    }
}