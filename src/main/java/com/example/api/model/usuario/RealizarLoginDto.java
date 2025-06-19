package com.example.api.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RealizarLoginDto(

        @Email(message = "Informe o seu e-mail de acesso")
        @NotBlank(message = "Obrigario informar o e-mail.")
        String email,

        @NotBlank(message = "Informe sua senha de acesso")
        @Size(min = 6, message = "Sua senha deve ter pelo menos 6 caracteres.")
        String senha

) {
}
