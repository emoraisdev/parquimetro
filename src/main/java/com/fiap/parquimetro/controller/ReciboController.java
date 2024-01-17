package com.fiap.parquimetro.controller;

import com.fiap.parquimetro.dto.ReciboDTO;
import com.fiap.parquimetro.service.ReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recibos")
public class ReciboController {

    @Autowired
    private ReciboService service;

    @GetMapping("/{id}")
    public ResponseEntity<ReciboDTO> get(@PathVariable String id){
        return ResponseEntity.ok().body(service.get(id));
    }

    @GetMapping("/bycondutor/{id}")
    public ResponseEntity<List<ReciboDTO>> getByCondutorId(@PathVariable String id){
        return ResponseEntity.ok().body(service.getByCondutorId(id));
    }

}
