package com.example.api.controller;

import com.example.api.dto.CustomPageResponseDto;
import com.example.api.dto.atualizar.AtualizarPacienteDto;
import com.example.api.dto.criar.CriarPacienteDto;
import com.example.api.model.Paciente;
import com.example.api.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pacientes", description = "operações/endpoints para pacientes")
public class PacienteController {

    final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @Operation(summary = "criar um paciente", description = "endpoint para criar um paciente")
    public ResponseEntity<Paciente> criarPaciente(@RequestBody @Valid CriarPacienteDto pacienteDto) {
        var paciente = pacienteService.criarPaciente(pacienteDto);
        return new ResponseEntity<>(paciente, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "listar pacientes", description = "endpoint para listagem de paciente com opção de paginação")
    public ResponseEntity<CustomPageResponseDto<Paciente>> listarPacientes(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var pacientes = pacienteService.listarPaciente(pageable);
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "listar um paciente", description = "endpoint para listar um paciente buscando pelo id")
    public ResponseEntity<Paciente> listarUmPaciente(@PathVariable("id") UUID id) {
        var paciente = pacienteService.listarUmPaciente(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "atualizar um paciente", description = "endpoint para atualizar um paciente passando o id e campos para atualizar")
    public ResponseEntity<Void> atualizarPaciente(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarPacienteDto pacienteDto) {
        pacienteService.atualizarPaciente(id, pacienteDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deletar um paciente", description = "endpoint para deletar um paciente passando o id")
    public ResponseEntity<Void> deletarPaciente(@PathVariable("id") UUID id) {
         pacienteService.deletarPaciente(id);
         return ResponseEntity.noContent().build();
    }
}
