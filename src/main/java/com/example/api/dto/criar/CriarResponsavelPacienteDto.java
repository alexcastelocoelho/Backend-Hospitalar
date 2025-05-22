package com.example.api.dto.criar;

import com.example.api.utils.Cpf.CpfValido;
import com.example.api.utils.Parentesco;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CriarResponsavelPacienteDto(

        @NotBlank
        @Size(min = 3, max = 60, message = "informe o nome do responsavel")
        String nome,

        @NotBlank(message = "Informe o CPF do responsavel")
        @CpfValido
        String cpf,

        @NotNull(message = "Informe a data de nascimento do responsavel")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Past(message = "Verifique se a data de nascimento está correta")
        LocalDate dataNascimento,

        @NotNull
        Parentesco parentesco,

        @NotBlank(message = "informe o contato do responsavel")
        @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Contato precisa ter o ddd junto com o número")
        String contato,

        @NotBlank(message = "Informe o endereço do responsavel")
        @Size(min = 8, max = 255, message = "Endereço precisa ter pelo menso 8 caracteres")
        String endereco


) {
}
