package com.example.api.controller;

import com.example.api.dto.atualizar.AtualizarResponsavelPacienteDto;
import com.example.api.dto.criar.CriarResponsavelPacienteDto;
import com.example.api.model.ResponsavelPaciente;
import com.example.api.service.ResponsavelPacienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelPacienteController {

    final ResponsavelPacienteService responsavelPacienteService;

    public ResponsavelPacienteController(ResponsavelPacienteService responsavelPacienteService) {
        this.responsavelPacienteService = responsavelPacienteService;
    }

    @PostMapping
    public ResponseEntity<ResponsavelPaciente> criarResponsavelPaciente(@RequestBody @Valid CriarResponsavelPacienteDto responsavelPacienteDto){
        var responsavelPaciente = responsavelPacienteService.criarResponsavel(responsavelPacienteDto);
        return new ResponseEntity<>(responsavelPaciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ResponsavelPaciente>> listarResponsavelPacientes(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var responsavelPacientes = responsavelPacienteService.listarResponsavelPacientes(pageable);
        return new ResponseEntity<>(responsavelPacientes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelPaciente> listarUmResponsavelPaciente(@PathVariable("id") UUID id) {
        var responsavelPaciente = responsavelPacienteService.listarUmResponsavelPaciente(id);
        return new ResponseEntity<>(responsavelPaciente, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarResponsavelPaciente(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarResponsavelPacienteDto responsavelPacienteDto) {
        responsavelPacienteService.atualizarResponsavelPaciente(id, responsavelPacienteDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarResponsavelPaciente(@PathVariable("id") UUID id) {
        responsavelPacienteService.deletarResponsavelPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
