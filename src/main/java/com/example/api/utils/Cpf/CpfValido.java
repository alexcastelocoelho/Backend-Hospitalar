package com.example.api.utils.Cpf;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfValidacao.class)
public @interface CpfValido {

    String message() default "Cpf invalido, verifique, por gentileza";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
