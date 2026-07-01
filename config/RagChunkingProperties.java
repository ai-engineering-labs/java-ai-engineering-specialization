package br.aritana.javaaiengineeringspecialization.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "rag.chunking")
public class RagChunkingProperties {

    private int maxSegmentSize = 300;
    private int maxOverlapSize = 30;
}
