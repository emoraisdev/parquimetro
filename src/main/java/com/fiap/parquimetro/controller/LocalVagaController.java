package com.fiap.parquimetro.controller;

import com.fiap.parquimetro.dto.LocalVagaDTO;
import com.fiap.parquimetro.service.impl.LocalVagaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/localVaga")
public class LocalVagaController {

    @Autowired
    private final LocalVagaServiceImpl localVagaService;
    @GetMapping("/{id}")
    public ResponseEntity<LocalVagaDTO> buscarLocalVaga(@PathVariable String id) {
        LocalVagaDTO resultado = localVagaService.buscarLocalVaga(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<LocalVagaDTO> salvarVaga(@RequestBody LocalVagaDTO localVagaDTO){
        return new ResponseEntity<>(localVagaService.salvarVaga(localVagaDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalVagaDTO> update(@PathVariable String id, @RequestBody LocalVagaDTO localVagaDTO) {
        LocalVagaDTO resultado = localVagaService.update(id, localVagaDTO);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVaga(@PathVariable String id) {
        localVagaService.deleteById(id);
        return new ResponseEntity<>("Vaga local deletada com sucesso.", HttpStatus.OK);
    }
}