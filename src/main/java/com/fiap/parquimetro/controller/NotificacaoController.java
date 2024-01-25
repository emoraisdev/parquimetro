package com.fiap.parquimetro.controller;

import com.fiap.parquimetro.dto.CondutorDTO;
import com.fiap.parquimetro.dto.NotificacaoDTO;
import com.fiap.parquimetro.dto.PermanenciaDTO;
import com.fiap.parquimetro.service.NotificacaoService;
import com.fiap.parquimetro.service.PermanenciaService;
import com.fiap.parquimetro.service.impl.NotificacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    private NotificacaoService service;

    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoDTO> obterNotificacao(@PathVariable String id) {
        return ResponseEntity.ok().body(service.get(id));
    }
    @PostMapping()
    public ResponseEntity<NotificacaoDTO> criar(@RequestBody NotificacaoDTO notificacaoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(notificacaoDTO));
    }
}
