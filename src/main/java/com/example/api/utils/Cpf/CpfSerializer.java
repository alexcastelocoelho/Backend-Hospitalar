package com.example.api.utils.Cpf;

import com.example.api.utils.MetodosUteis;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CpfSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String cpf, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(MetodosUteis.imprimeCPF(cpf));

    }
}
