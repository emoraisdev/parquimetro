package com.fiap.parquimetro.scheduler;

import com.fiap.parquimetro.service.impl.NotificacaoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@Slf4j
public class NotificacaoScheduler {

    @Autowired
    private NotificacaoServiceImpl notificacaoService;

    @Scheduled(fixedRateString = "${periodo.execucao.consumo}") //cada 1 minuto (60.000 milissegundos)

    public void consomeNotificacoes() {
        log.info("Iniciando consumo de notificações.");
        notificacaoService.processsarNotificacoesNaoLidas();
    }
}
