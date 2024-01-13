package com.fiap.parquimetro.controller;

import com.fiap.parquimetro.dto.VeiculoDTO;
import com.fiap.parquimetro.model.Veiculo;
import com.fiap.parquimetro.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping()
    public ResponseEntity<VeiculoDTO> criar(@RequestBody VeiculoDTO veiculoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(veiculoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> get(@PathVariable String id){
        return ResponseEntity.ok().body(service.get(id));
    }
}
