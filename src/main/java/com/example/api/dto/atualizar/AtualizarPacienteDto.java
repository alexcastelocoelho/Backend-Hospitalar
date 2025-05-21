package com.example.api.dto.atualizar;

import jakarta.validation.constraints.*;

public record AtualizarPacienteDto(

        @Size(min = 3, max = 60, message = "informe o nome do paciente")
        String nome,

        @Min(value = 3, message = "idade minima do paciente precisa ser 3 anos")
        @Max(value = 110, message = "idade máxima permitida é 110 anos")
        Integer idade,

        @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Contato precisa ter o ddd junto com o número")
        String contato,

        @Size(min = 8, max = 255, message = "Endereço precisa ter pelo menso 8 caracteres")
        String endereco


) {
}
