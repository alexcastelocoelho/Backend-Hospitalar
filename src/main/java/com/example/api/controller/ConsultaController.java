package com.example.api.controller;

import com.example.api.dto.atualizar.AtualizarConsultaDto;
import com.example.api.dto.criar.CriarConsultaDto;
import com.example.api.model.Consulta;
import com.example.api.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }


    @PostMapping
    public ResponseEntity<Consulta> criarConsulta(@RequestBody @Valid CriarConsultaDto consultaDto) {
        var consulta = consultaService.criarConsulta(consultaDto);
        return new ResponseEntity<>(consulta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Consulta>> listarConsultas(@PageableDefault(size = 10, sort = "status") Pageable pageable) {
        var consultas = consultaService.listarConsultas(pageable);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> listarUmaConsulta(@PathVariable("id") UUID id) {
        var consulta = consultaService.listarUmaConsulta(id);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarConsulta(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarConsultaDto consultaDto) {
        consultaService.atualizarConsulta(id, consultaDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable("id") UUID id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }


}
