package com.example.api.dto.atualizar;

import com.example.api.utils.Parentesco;
import jakarta.validation.constraints.*;

public record AtualizarResponsavelPacienteDto(

        @Size(min = 3, max = 60, message = "informe o nome do responsavel")
        String nome,

        Parentesco parentesco,

        @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Contato precisa ter o ddd junto com o número")
        String contato,

        @Size(min = 8, max = 255, message = "Endereço precisa ter pelo menso 8 caracteres")
        String endereco


) {
}
