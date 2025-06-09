package com.example.api.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastrarUsuarioDto(

        @Email(message = "Informe um e-mail valido")
        @NotBlank(message = "Obrigario informar o e-mail.")
        String login,

        @NotBlank(message = "Defina sua senha")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
        String password,

        @NotNull(message = "Defina o tipo de usuario ")
        UserRole role

) {
}
