package com.fiap.parquimetro.service.impl;


import com.fiap.parquimetro.dto.PermanenciaDTO;
import com.fiap.parquimetro.model.Notificacao;
import com.fiap.parquimetro.model.Permanencia;
import com.fiap.parquimetro.model.enums.FormaPagamentoStatus;
import com.fiap.parquimetro.model.enums.NotificaoStatus;
import com.fiap.parquimetro.model.enums.PermanenciaStatus;
import com.fiap.parquimetro.repository.PermanenciaRepository;
import com.fiap.parquimetro.service.PermanenciaService;
import com.fiap.parquimetro.service.impl.NotificacaoServiceImpl;
import com.fiap.parquimetro.service.impl.VeiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermanenciaServiceImpl implements PermanenciaService {

    @Value("${notificacao.mensagem.tempo.variavel}")
    private String notificacaoMensagemTempoVariavel;

    @Value("${notificacao.mensagem.tempo.fixo}")
    private String notificacaoMensagemTempoFixo;
    @Autowired
    PermanenciaRepository permanenciaRepository;

    @Autowired
    VeiculoServiceImpl veiculoService;

    @Autowired
    NotificacaoServiceImpl notificacaoService;

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

    public void verificarPermanenciasAtivas() {
        List<Permanencia> permanenciasAtivas = permanenciaRepository.findByStatus(PermanenciaStatus.EM_ANDAMENTO);

        permanenciasAtivas.parallelStream()
                .forEach(this::processaPermanencia);
    }

    public void processaPermanencia(Permanencia permanencia){
        LocalDateTime now = LocalDateTime.now();
        Duration duracao = Duration.between(permanencia.getEntrada(), now);

        if (permanencia.getTempoFixo() && duracao.toHours() >= permanencia.getHorasTempoFixo()) {
            permanencia.setStatus(PermanenciaStatus.FINALIZADA);
            permanenciaRepository.save(permanencia);
        } else if (!permanencia.getTempoFixo() && duracao.toHours() >= permanencia.getHorasTempoFixo()) {
            Integer novaHora = permanencia.getHorasTempoFixo() + 1;
            permanencia.setHorasTempoFixo(novaHora);
            permanenciaRepository.save(permanencia);
        } else {
            verificarProximidadeFim(permanencia);
        }
    }

    public void verificarProximidadeFim(Permanencia permanencia){
        if (isProximoFim(permanencia)) {
            Notificacao notificacao = Notificacao.builder()
                    .condutor(permanencia.getCondutor())
                    .dateTime(LocalDateTime.now())
                    .status(NotificaoStatus.NAO_LIDA)
                    .build();

            if (permanencia.getTempoFixo()) {
                notificacao.setMensagem(notificacaoMensagemTempoFixo);
            } else {
                notificacao.setMensagem(notificacaoMensagemTempoVariavel);
            }
            notificacaoService.salvarNotificacao(notificacao);
        }
    }

    private boolean isProximoFim(Permanencia permanencia) {
        LocalDateTime now = LocalDateTime.now();
        Duration tempoRestante = Duration.between(now, permanencia.getSaida());
        return tempoRestante.toMinutes() <= 15;
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
