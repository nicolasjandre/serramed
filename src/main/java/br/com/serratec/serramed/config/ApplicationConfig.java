package br.com.serratec.serramed.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    ModelMapper mapper() {
        return new ModelMapper();
    }
}