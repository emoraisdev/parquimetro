package com.fiap.parquimetro.service;


import com.fiap.parquimetro.dto.PermanenciaDTO;
import com.fiap.parquimetro.model.Permanencia;
import com.fiap.parquimetro.model.enums.FormaPagamentoStatus;
import com.fiap.parquimetro.model.enums.PermanenciaStatus;
import com.fiap.parquimetro.repository.PermanenciaRepository;
import com.fiap.parquimetro.service.impl.VeiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermanenciaServiceImpl implements PermanenciaService{

    @Autowired
    PermanenciaRepository permanenciaRepository;

    @Autowired
    VeiculoServiceImpl veiculoService;

    @Override
    public PermanenciaDTO criar(PermanenciaDTO dto) {
        var permanencia = toEntity(dto);

        return toDTO(permanenciaRepository.save(permanencia));
    }

    @Override
    public PermanenciaDTO get(String id) {
        return null;
    }

    @Override
    public List<PermanenciaDTO> getByCondutorId(String id) {
        return null;
    }

    @Override
    public void update(PermanenciaDTO dto) {

    }

    public PermanenciaDTO toDTO(Permanencia permanencia) {
        return new PermanenciaDTO(
                permanencia.getId(),
                permanencia.getCondutor(),
                permanencia.getVeiculo(),
                permanencia.getLocal(),
                permanencia.getEntrada(),
                permanencia.getSaida(),
                permanencia.getTipoPagamento(),
                permanencia.getStatusPagamento().getValue(),
                permanencia.getStatus().getValue(),
                permanencia.getTempoFixo(),
                permanencia.getHorasTempoFixo()
        );
    }

    public Permanencia toEntity(PermanenciaDTO dto) {
        return new Permanencia(
                dto.id(),
                dto.condutor(),
                dto.veiculo(),
                dto.local(),
                dto.entrada(),
                dto.saida(),
                dto.tipoPagamento(),
                FormaPagamentoStatus.fromValue( dto.statusPagamento()),
                PermanenciaStatus.fromValue(dto.status()),
                dto.tempoFixo(),
                dto.horasTempoFixo()
        );
    }
}
