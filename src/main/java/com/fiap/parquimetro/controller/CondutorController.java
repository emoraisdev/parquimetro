package com.fiap.parquimetro.controller;

import com.fiap.parquimetro.dto.CondutorDTO;
import com.fiap.parquimetro.service.impl.CondutorServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/condutor")
public class CondutorController {

    private final CondutorServiceImpl condutorService;

    @PostMapping()
    public ResponseEntity<CondutorDTO> createCondutor(@RequestBody @Valid CondutorDTO condutorDTO) {
        return new ResponseEntity<>(condutorService.criar(condutorDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondutorDTO> obterCondutor(@PathVariable String id) {
       return ResponseEntity.ok().body(condutorService.get(id));
    }

    @GetMapping()
    public ResponseEntity<List<CondutorDTO>> listarCondutor() {
        return new ResponseEntity<>(condutorService.getCondutorList(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCondutor(@RequestBody @Valid CondutorDTO condutorDTO) {
        condutorService.update(condutorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteCondutor(@PathVariable String id) {
        condutorService.deleteById(id);
    }
}
