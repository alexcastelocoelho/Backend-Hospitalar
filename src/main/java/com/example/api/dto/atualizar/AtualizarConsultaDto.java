package com.example.api.dto.atualizar;

import com.example.api.model.Medico;
import com.example.api.utils.StatusConsulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalTime;

public record AtualizarConsultaDto(


        @JsonFormat(pattern = "dd/MM/yyyy")
        @Future(message = "Verifique a data definida")
        LocalDate dataConsulta,

        LocalTime horaConsulta,

        Medico medico,

        StatusConsulta status

) {
}
