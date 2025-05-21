package com.example.api.controller;

import com.example.api.dto.atualizar.AtualizarPacienteDto;
import com.example.api.dto.criar.CriarPacienteDto;
import com.example.api.model.Paciente;
import com.example.api.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> criarPaciente(@RequestBody @Valid CriarPacienteDto pacienteDto) {
        var paciente = pacienteService.criarPaciente(pacienteDto);
        return new ResponseEntity<>(paciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> listarPacientes(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var pacientes = pacienteService.listarPaciente(pageable);
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listarUmPaciente(@PathVariable("id") UUID id) {
        var paciente = pacienteService.listarUmPaciente(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarPaciente(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarPacienteDto pacienteDto) {
        pacienteService.atualizarPaciente(id, pacienteDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable("id") UUID id) {
         pacienteService.deletarPaciente(id);
         return ResponseEntity.noContent().build();
    }
}
