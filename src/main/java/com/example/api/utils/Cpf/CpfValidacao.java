package com.example.api.utils.Cpf;

import com.example.api.utils.MetodosUteis;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidacao implements ConstraintValidator<CpfValido, String> {
    @Override
    public void initialize(CpfValido constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (MetodosUteis.isCPF(cpf)) {
            return true;
        }
        return false;
    }
}
