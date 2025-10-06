package com.api.nasa.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String titulo;

    @Value("${application-description}")
    private String descripción;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(titulo)
                        .description(descripción)
                        .termsOfService("terms")
                        .contact(new Contact().email("@example.dev"))
                        .license(new License().name("GNU"))
                        .version("1.0")
                );
    }

}
