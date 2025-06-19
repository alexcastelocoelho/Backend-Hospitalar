package com.example.api.controller;

import com.example.api.dto.atualizar.AtualizarMedicoDto;

import com.example.api.dto.criar.CriarMedicoDto;
import com.example.api.model.Medico;

import com.example.api.service.MedicoService;
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
@RequestMapping("/medico")
@Tag(name = "Medico", description = "operações/endpoints para medico")
public class MedicoController {

    final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    @Operation(summary = "criar um medico", description = "endpoint para criar um medico")
    public ResponseEntity<Medico> criarMedico(@RequestBody @Valid CriarMedicoDto medicoDto) {
        var medico = medicoService.criarMedico(medicoDto);
        return new ResponseEntity<>(medico, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "listar medicos", description = "endpoint para listagem de medicos com opção de paginação")
    public ResponseEntity<Page<Medico>> listarMedicos(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var medicos = medicoService.listarMedicos(pageable);
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "listar um medico", description = "endpoint para listar um medico buscando pelo id")
    public ResponseEntity<Medico> listarUmMedico(@PathVariable("id") UUID id) {
        var medico = medicoService.listarUmMedico(id);
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "atualizar um medico", description = "endpoint para atualizar um medico passando o id e campos para atualizar")
    public ResponseEntity<Void> atualizarMedico(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarMedicoDto medicoDto) {
        medicoService.atualizarMedico(id, medicoDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "deletar um medico", description = "endpoint para deletar um medico passando o id")
    public ResponseEntity<Void> deletarMedico(@PathVariable("id") UUID id) {
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }

}
