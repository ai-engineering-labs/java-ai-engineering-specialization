package br.aritana.javaaiengineeringspecialization.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "rag.embedding")
public class RagEmbeddingProperties {

    private String baseUrl = "http://localhost:11434";
    private String modelName = "nomic-embed-text";
    private Duration timeout = Duration.ofSeconds(60);
}
