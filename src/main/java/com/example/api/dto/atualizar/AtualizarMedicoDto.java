package com.example.api.dto.atualizar;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AtualizarMedicoDto(


        @Size(min = 3, max = 60, message = "Informe o nome do medico")
        String nome,


        @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Contato precisa ter o ddd junto com o número")
        String contato,


        @Size(min = 8, max = 255, message = "Endereço precisa ter pelo menso 8 caracteres")
        String endereco,


        @Email(message = "informe um email valido")
        String email


) {
}
