package com.duarte.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duarte.model.Transferencia;
import com.duarte.repository.TransferenciaRepository;
import com.duarte.service.TaxaService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired private TransferenciaRepository repository;
    @Autowired private TaxaService taxaService;

    @PostMapping
    public ResponseEntity<?> agendar(@RequestBody Transferencia dto) {
        try {
            dto = taxaService.calcularTaxa(dto);
            repository.save(dto);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public List<Transferencia> listar() {
        return repository.findAll();
    }
}
