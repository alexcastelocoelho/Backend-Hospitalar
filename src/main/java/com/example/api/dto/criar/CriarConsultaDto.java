package com.example.api.dto.criar;

import com.example.api.model.Medico;
import com.example.api.model.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;


public record CriarConsultaDto(

        @NotNull(message = "Informe a data da consulta")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Future(message = "Verifique a data definida")
        LocalDate dataConsulta,


        @NotNull(message = "Informe o horário da consulta")
        LocalTime horaConsulta,


        @NotNull(message = "Informe  o paciente da consulta")
        Paciente paciente,


        @NotNull(message = "Informe o medico da consulta")
        Medico medico,

        @NotBlank(message = "Informe o status da consulta")
        String status

) {
}
