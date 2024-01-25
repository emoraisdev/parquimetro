package com.fiap.parquimetro.service.impl;

import com.fiap.parquimetro.dto.NotificacaoDTO;
import com.fiap.parquimetro.exception.EntityNotFoundException;
import com.fiap.parquimetro.model.Condutor;
import com.fiap.parquimetro.model.Notificacao;
import com.fiap.parquimetro.model.enums.NotificaoStatus;
import com.fiap.parquimetro.service.NotificacaoService;
import com.fiap.parquimetro.service.PermanenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.fiap.parquimetro.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NotificacaoServiceImpl implements NotificacaoService {
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Override
    public NotificacaoDTO criar(NotificacaoDTO dto) {
        var notificacao = toEntity(dto);

        return toDTO(notificacaoRepository.save(notificacao));
    }

    @Override
    public NotificacaoDTO get(String id) {
        return toDTO(notificacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Notificacao")));
    }

    @Override
    public void salvarNotificacao(Notificacao notificacao) {
        notificacaoRepository.save(notificacao);
    }

    public void processsarNotificacoesNaoLidas(){
        List<Notificacao> notificacoesNaoLidas = notificacaoRepository.findByStatus(NotificaoStatus.NAO_LIDA);

        notificacoesNaoLidas.parallelStream()
                .forEach(this::processarNotificacao);
    }

    private void processarNotificacao(Notificacao notificacao) {
        enviarNotificacao(notificacao);
        notificacao.setStatus(NotificaoStatus.LIDA);
        notificacaoRepository.save(notificacao);
    }

    private void enviarNotificacao(Notificacao notificacao){
        //integração com outros sistemas para envio
        log.info("Mensagem enviada {}", notificacao.toString());
    }

    public NotificacaoDTO toDTO(Notificacao notificacao) {
        return new NotificacaoDTO(
                notificacao.getId(),
                notificacao.getMensagem(),
                notificacao.getCondutor().getId(),
                notificacao.getDateTime(),
                notificacao.getStatus().getValue()
        );
    }

    public Notificacao toEntity(NotificacaoDTO dto) {
        return new Notificacao(
                dto.id(),
                dto.mensagem(),
                new Condutor(dto.idCondutor()),
                dto.dateTime(),
                dto.status() != null ?  NotificaoStatus.fromValue(dto.status()) : null
        );
    }
}
