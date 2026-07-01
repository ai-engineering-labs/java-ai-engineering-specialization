package br.aritana.javaaiengineeringspecialization.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "rag.chat")
public class RagChatProperties {

    private String baseUrl = "http://localhost:11434";
    private String modelName = "llama3.2:1b";
    private double temperature = 0.2;
    private Duration timeout = Duration.ofSeconds(60);
}
