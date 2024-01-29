package com.fiap.parquimetro.service.impl;


import com.fiap.parquimetro.dto.LocalVagaDTO;
import com.fiap.parquimetro.dto.PermanenciaDTO;
import com.fiap.parquimetro.exception.BusinessException;
import com.fiap.parquimetro.exception.EntityNotFoundException;
import com.fiap.parquimetro.model.*;
import com.fiap.parquimetro.model.enums.NotificaoStatus;
import com.fiap.parquimetro.model.enums.PagamentoStatus;
import com.fiap.parquimetro.model.enums.PermanenciaStatus;
import com.fiap.parquimetro.model.enums.TipoFormaPagamento;
import com.fiap.parquimetro.repository.PermanenciaRepository;
import com.fiap.parquimetro.service.PermanenciaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    @Autowired
    CondutorServiceImpl condutorService;

    @Autowired
    LocalVagaServiceImpl localVagaService;

    @Autowired
    ReciboServiceImpl reciboService;

    @Override
    public PermanenciaDTO criar(PermanenciaDTO dto) {
        var permanencia = toEntity(dto);
        verificaParemetros(permanencia);
        permanencia.setEntrada(LocalDateTime.now());
        permanencia.setPagamentoStatus(PagamentoStatus.NAO_PAGO);
        permanencia.setPermanenciaStatus(PermanenciaStatus.EM_ANDAMENTO);
        permanencia = permanenciaRepository.save(permanencia);

        if (permanencia.getTempoFixo()) {
            permanencia.setSaida(permanencia.getEntrada().plusHours(permanencia.getHorasTempoFixo()));
            permanencia.setPagamentoStatus(PagamentoStatus.PAGO);
            geraRecibo(permanencia, true);
            permanencia = permanenciaRepository.save(permanencia);
        }
        return toDTO(permanencia);
    }

    @Override
    public PermanenciaDTO get(String id) {
        return toDTO(permanenciaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Permanencia")));
    }

    @Override
    public List<PermanenciaDTO> getByCondutorId(String id) {
        return null;
    }

    @Override
    public void update(PermanenciaDTO dto) {
        get(dto.id());
        permanenciaRepository.save(toEntity(dto));
    }

    private void verificaParemetros(Permanencia permanencia) {

        var condutor = condutorService.get(permanencia.getCondutor().getId());

        if( permanencia.getTipoPagamento() == null ) {
            permanencia.setTipoPagamento(condutor.formaPagamentoPreferida());
        }

        veiculoService.get(permanencia.getVeiculo().getId());

        localVagaService.buscarLocalVaga(permanencia.getLocal().getId());


        if (permanencia.getTempoFixo() && permanencia.getHorasTempoFixo() == 0){
            throw new BusinessException("Para períodos de estacionamento fixos deve ser informado a quantidade de horas.");
        }

        if (!permanencia.getTempoFixo() && permanencia.getTipoPagamento() == TipoFormaPagamento.PIX){
            throw new BusinessException("Pagamento via PIX disponível apenas para períodos de estacionamento fixos.");
        }

    }

    public void finalizaPermanencia(String id){
        Permanencia permanencia = permanenciaRepository.findById(id).orElseThrow( ()-> new BusinessException("Permanencia não encontrada"));
        permanencia.setPermanenciaStatus(PermanenciaStatus.FINALIZADA);
        permanencia.setPagamentoStatus(PagamentoStatus.PAGO);
        permanencia.setSaida(LocalDateTime.now());
        geraRecibo(permanencia,false);
        update(toDTO(permanencia));
    }

    private void geraRecibo(Permanencia permanencia, boolean horaFixa) {
        Recibo recibo = null;
        LocalVagaDTO localVagaDTO = localVagaService.buscarLocalVaga(permanencia.getLocal().getId());
        permanencia.getLocal().setValorHoraFixa(localVagaDTO.valorHoraFixa());
        permanencia.getLocal().setValorHoraFixa(localVagaDTO.valorHoraVariavel());

        if (horaFixa) {
            Duration duracao = Duration.ofHours(permanencia.getHorasTempoFixo());
            BigDecimal valorTotal = permanencia.getLocal().getValorHoraFixa()
                    .multiply(new BigDecimal(permanencia.getHorasTempoFixo()));

            recibo = new Recibo(
                    null,
                    permanencia.getLocal().getValorHoraFixa(),
                    valorTotal,
                    duracao,
                    permanencia,
                    permanencia.getCondutor()
            );
        } else {
            Duration duracao = Duration.between(permanencia.getEntrada(), permanencia.getSaida());
            BigDecimal valorTotal = permanencia.getLocal().getValorHoraVariavel()
                    .multiply(new BigDecimal(duracao.toHours()));

            recibo = new Recibo(
                    null,
                    permanencia.getLocal().getValorHoraVariavel(),
                    valorTotal,
                    duracao,
                    permanencia,
                    permanencia.getCondutor()
            );
        }

        reciboService.salva(recibo);
    }

    public void verificarPermanenciasAtivas() {
        List<Permanencia> permanenciasAtivas = permanenciaRepository.findByPermanenciaStatus(PermanenciaStatus.EM_ANDAMENTO);

        permanenciasAtivas.parallelStream()
                .forEach(this::processaPermanencia);
    }

    public void processaPermanencia(Permanencia permanencia){
        LocalDateTime now = LocalDateTime.now();
        Duration duracao = Duration.between(permanencia.getEntrada(), now);

        if (permanencia.getTempoFixo() && duracao.toHours() >= permanencia.getHorasTempoFixo()) {
            permanencia.setPermanenciaStatus(PermanenciaStatus.FINALIZADA);
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

        Duration tempoRestante = Duration.ZERO;
        if (permanencia.getTempoFixo()) {
            tempoRestante = Duration.between(now, permanencia.getSaida());
        } else {
            var minutos = Duration.between(permanencia.getEntrada(), now).toMinutes() % 60;

            tempoRestante = Duration.ofHours(1).minusMinutes(minutos);
        }

        return tempoRestante.toMinutes() <= 15;
    }

    public PermanenciaDTO toDTO(Permanencia permanencia) {
        return new PermanenciaDTO(
                permanencia.getId(),
                permanencia.getCondutor().getId(),
                permanencia.getVeiculo().getId(),
                permanencia.getLocal().getId(),
                permanencia.getEntrada(),
                permanencia.getSaida(),
                permanencia.getTipoPagamento(),
                permanencia.getPagamentoStatus(),
                permanencia.getPermanenciaStatus(),
                permanencia.getTempoFixo(),
                permanencia.getHorasTempoFixo()
        );
    }

    public Permanencia toEntity(PermanenciaDTO dto) {
        return new Permanencia(
                dto.id(),
                new Condutor(dto.idCondutor()),
                new Veiculo(dto.idVeiculo()),
                new LocalVaga(dto.idLocalVaga()),
                dto.entrada(),
                dto.saida(),
                dto.tipoPagamento(),
                dto.pagamentoStatus(),
                dto.permanenciaStatus(),
                dto.tempoFixo(),
                dto.horasTempoFixo()
        );
    }
}
