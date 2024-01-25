package com.fiap.parquimetro.scheduler;

import com.fiap.parquimetro.service.impl.PermanenciaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Slf4j
public class PermanenciaScheduler {

    @Autowired
    private PermanenciaServiceImpl permanenciaService;

    @Scheduled(fixedRateString = "${periodo.execucao.verificacao}")//cada 2 minutos
    public void verificarPermanenciasAtivas() {
        log.info("Iniciando verificação de permanencias.");
        permanenciaService.verificarPermanenciasAtivas();
    }
}
