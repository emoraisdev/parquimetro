package com.fiap.parquimetro.controller;

import com.fiap.parquimetro.dto.PermanenciaDTO;
import com.fiap.parquimetro.service.PermanenciaService;
import com.fiap.parquimetro.service.impl.PermanenciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/permanencia")
public class PermanenciaController {

    @Autowired
    private PermanenciaServiceImpl service;

    @PostMapping()
    public ResponseEntity<PermanenciaDTO> criar(@RequestBody PermanenciaDTO permanenciaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(permanenciaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermanenciaDTO> obter(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.get(id));
    }

    @PutMapping
    public ResponseEntity<PermanenciaDTO> finaliza(@RequestParam String id){
        service.finalizaPermanencia(id);
        return ResponseEntity.ok().build();
    }
}
