package com.fiap.parquimetro.controller;

import com.fiap.parquimetro.dto.LocalVagaDTO;
import com.fiap.parquimetro.model.Permanencia;
import com.fiap.parquimetro.service.impl.LocalVagaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/localVaga")
public class LocalVagaController {

    private final LocalVagaServiceImpl localVagaService;
    @GetMapping("/{id}")
    public ResponseEntity<LocalVagaDTO> buscarLocalVaga(@PathVariable String id) {
        LocalVagaDTO resultado = localVagaService.buscarLocalVaga(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<BigDecimal> calcularValorEstacionamento(@RequestBody LocalVagaDTO localVagaDTO,
                                                                  @RequestBody Permanencia permanencia) {
        BigDecimal resultado = localVagaService.calcularValorEstacionamento(localVagaDTO, permanencia);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public  ResponseEntity<LocalVagaDTO> salvarVaga(@RequestBody LocalVagaDTO localVagaDTO){
        return new ResponseEntity<>(localVagaService.salvarVaga(localVagaDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalVagaDTO> atualizarVaga(@PathVariable String id, @RequestBody LocalVagaDTO localVagaDTO) {
        LocalVagaDTO resultado = localVagaService.update(id, localVagaDTO);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}