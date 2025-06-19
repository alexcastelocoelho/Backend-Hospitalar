package com.example.api.controller;

import com.example.api.dto.atualizar.AtualizarConsultaDto;
import com.example.api.dto.criar.CriarConsultaDto;
import com.example.api.model.Consulta;
import com.example.api.service.ConsultaService;
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
@RequestMapping("/consulta")
@Tag(name = "Consulta", description = "operações/endpoints para consulta")
public class ConsultaController {

    final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }


    @PostMapping
    @Operation(summary = "criar uma consulta", description = "endpoint para criar uma consulta")
    public ResponseEntity<Consulta> criarConsulta(@RequestBody @Valid CriarConsultaDto consultaDto) {
        var consulta = consultaService.criarConsulta(consultaDto);
        return new ResponseEntity<>(consulta, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "listar consultas", description = "endpoint para listagem de consultas com opção de paginação")
    public ResponseEntity<Page<Consulta>> listarConsultas(@PageableDefault(size = 10, sort = "status") Pageable pageable) {
        var consultas = consultaService.listarConsultas(pageable);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "listar uma consulta", description = "endpoint para listar uma consulta buscando pelo id")
    public ResponseEntity<Consulta> listarUmaConsulta(@PathVariable("id") UUID id) {
        var consulta = consultaService.listarUmaConsulta(id);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "atualizar uma consulta", description = "endpoint para atualizar uma consulta passando o id e campos para atualizar")
    public ResponseEntity<Void> atualizarConsulta(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarConsultaDto consultaDto) {
        consultaService.atualizarConsulta(id, consultaDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deletar uma consulta", description = "endpoint para deletar uma consulta passando o id")
    public ResponseEntity<Void> deletarConsulta(@PathVariable("id") UUID id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }


}
