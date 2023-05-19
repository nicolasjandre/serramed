package br.com.serratec.serramed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Serramed")
                        .description("API REST para gerenciamento hospitalar")
                        .version("1.0")
                        .termsOfService("Open Source")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://serratec.org/")));
    }
}