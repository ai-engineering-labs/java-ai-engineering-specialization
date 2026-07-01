package br.aritana.javaaiengineeringspecialization.config;

import br.aritana.javaaiengineeringspecialization.assistant.RagAssistant;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        RagChunkingProperties.class,
        RagEmbeddingProperties.class,
        RagRetrievalProperties.class
})
public class RagConfig {

    @Bean
    public EmbeddingModel embeddingModel(RagEmbeddingProperties properties) {
        return OllamaEmbeddingModel.builder()
                .baseUrl(properties.getBaseUrl())
                .modelName(properties.getModelName())
                .timeout(properties.getTimeout())
                .build();
    }

    @Bean
    public InMemoryEmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStoreDelegate(InMemoryEmbeddingStore<TextSegment> embeddingStore) {
        return embeddingStore;
    }

    @Bean
    public ContentRetriever contentRetriever(
            EmbeddingStore<TextSegment> embeddingStore,
            EmbeddingModel embeddingModel,
            RagRetrievalProperties properties) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(properties.getMaxResults())
                .minScore(properties.getMinScore())
                .build();
    }

    @Bean
    public RagAssistant ragAssistant(OllamaChatModel chatModel, ContentRetriever contentRetriever) {
        return AiServices.builder(RagAssistant.class)
                .chatModel(chatModel)
                .contentRetriever(contentRetriever)
                .build();
    }
}
