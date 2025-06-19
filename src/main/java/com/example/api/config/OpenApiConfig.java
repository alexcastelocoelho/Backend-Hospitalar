package com.example.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gestao Hospital",
                version = "1.0.0",
                description = "Documentação da API para sistema de gestão de ambiente hospitalar",
                contact = @Contact(
                        name = "Alex Coelho",
                        email = "alexCoelhoCas@email.com"

                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class OpenApiConfig {
}
