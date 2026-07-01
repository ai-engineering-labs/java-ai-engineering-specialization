package br.aritana.javaaiengineeringspecialization.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "rag.retrieval")
public class RagRetrievalProperties {

    private int maxResults = 3;
    private double minScore = 0.0;
}
