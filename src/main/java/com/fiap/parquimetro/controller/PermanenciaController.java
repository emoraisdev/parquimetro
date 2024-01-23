package com.fiap.parquimetro.controller;

import com.fiap.parquimetro.dto.PermanenciaDTO;
import com.fiap.parquimetro.service.PermanenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permanencia")
public class PermanenciaController {

    @Autowired
    private PermanenciaService service;

    @PostMapping()
    public ResponseEntity<PermanenciaDTO> criar(@RequestBody PermanenciaDTO permanenciaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(permanenciaDTO));
    }
}
