package com.example.api.controller;

import com.example.api.dto.CustomPageResponseDto;
import com.example.api.dto.atualizar.AtualizarResponsavelPacienteDto;
import com.example.api.dto.criar.CriarResponsavelPacienteDto;
import com.example.api.model.ResponsavelPaciente;
import com.example.api.service.ResponsavelPacienteService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/responsavel")
@Tag(name = "Responsavel", description = "operações/endpoints para responsavel")
public class ResponsavelPacienteController {

    final ResponsavelPacienteService responsavelPacienteService;

    public ResponsavelPacienteController(ResponsavelPacienteService responsavelPacienteService) {
        this.responsavelPacienteService = responsavelPacienteService;
    }

    @PostMapping
    @Operation(summary = "criar um responsavel", description = "endpoint para criar um responsavel")
    public ResponseEntity<ResponsavelPaciente> criarResponsavelPaciente(@RequestBody @Valid CriarResponsavelPacienteDto responsavelPacienteDto){
        var responsavelPaciente = responsavelPacienteService.criarResponsavel(responsavelPacienteDto);
        return new ResponseEntity<>(responsavelPaciente, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "listar responsaveis", description = "endpoint para listagem de responsaveis com opção de paginação")
    public ResponseEntity<CustomPageResponseDto<ResponsavelPaciente>> listarResponsavelPacientes(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var responsavelPacientes = responsavelPacienteService.listarResponsavelPacientes(pageable);
        return new ResponseEntity<>(responsavelPacientes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "listar um responsavel", description = "endpoint para listar um responsavel buscando pelo id")
    public ResponseEntity<ResponsavelPaciente> listarUmResponsavelPaciente(@PathVariable("id") UUID id) {
        var responsavelPaciente = responsavelPacienteService.listarUmResponsavelPaciente(id);
        return new ResponseEntity<>(responsavelPaciente, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    @Operation(summary = "atualizar um responsavel", description = "endpoint para atualizar um responsavel passando o id e campos para atualizar")
    public ResponseEntity<Void> atualizarResponsavelPaciente(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarResponsavelPacienteDto responsavelPacienteDto) {
        responsavelPacienteService.atualizarResponsavelPaciente(id, responsavelPacienteDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "deletar um responsavel", description = "endpoint para deletar um responsavel passando o id")
    public ResponseEntity<Void> deletarResponsavelPaciente(@PathVariable("id") UUID id) {
        responsavelPacienteService.deletarResponsavelPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
