package com.example.api.dto.criar;


import com.example.api.utils.Cpf.CpfValido;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CriarMedicoDto(

        @NotBlank
        @Size(min = 3, max = 60, message = "Informe o nome do medico")
        String nome,

        @NotBlank(message = "Informe o CRM do medico")
        @Pattern(regexp = "^CRM-[A-Z]{2} \\d{4,6}$", message = "Verifique o formato ou valor do crm informado")
        String numeroConselho,

        @NotBlank(message = "Informe o CPF do medico")
        @CpfValido
        String cpf,

        @NotBlank(message = "informe o contato do medico")
        @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Contato precisa ter o ddd junto com o número")
        String contato,


        @NotBlank(message = "Informe o endereço do medico")
        @Size(min = 8, max = 255, message = "Endereço precisa ter pelo menso 8 caracteres")
        String endereco,


        @NotBlank(message = "informe o email do medico")
        @Email(message = "informe um email valido")
        String email
) {
}
