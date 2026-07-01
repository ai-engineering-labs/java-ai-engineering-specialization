package br.aritana.javaaiengineeringspecialization.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Java AI Engineering Specialization API")
                        .description("API do projeto RAG incremental com Spring Boot e LangChain4j")
                        .version("0.0.1"));
    }
}
