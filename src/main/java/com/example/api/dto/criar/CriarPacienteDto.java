package com.example.api.dto.criar;

import com.example.api.utils.Cpf.CpfValido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CriarPacienteDto(

        @NotBlank
        @Size(min = 3, max = 60, message = "informe o nome do paciente")
        String nome,

        @NotNull(message = "informe a idade do paciente")
        @Min(value = 3, message = "idade minima do paciente precisa ser 3 anos")
        @Max(value = 110, message = "idade máxima permitida é 110 anos")
        Integer idade,

        @NotBlank(message = "Informe o CPF do paciente")
        @CpfValido
        String cpf,


        @NotNull(message = "Informe a data de nascimento do paciente")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Past(message = "Verifique se a data de nascimento está correta")
        LocalDate dataNascimento,


        @NotBlank(message = "informe o contato do paciente")
        @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Contato precisa ter o ddd junto com o número")
        String contato,


        @NotBlank(message = "Informe o endereço do paciente")
        @Size(min = 8, max = 255, message = "Endereço precisa ter pelo menso 8 caracteres")
        String endereco

) {
}
