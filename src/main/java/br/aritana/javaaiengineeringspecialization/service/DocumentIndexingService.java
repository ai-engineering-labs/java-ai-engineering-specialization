package br.aritana.javaaiengineeringspecialization.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentIndexingService {

    private final EmbeddingModel embeddingModel;
    private final InMemoryEmbeddingStore<TextSegment> embeddingStore;
    private final DocumentSplitterService documentSplitterService;

    public int index(List<TextSegment> segments) {
        if (segments.isEmpty()) {
            return 0;
        }

        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();
        embeddingStore.addAll(embeddings, segments);
        return segments.size();
    }

    public int index(Document document) {
        return index(documentSplitterService.chunk(document));
    }

    public int indexFile(String path) {
        return index(documentSplitterService.chunkFile(path));
    }

    public int indexDirectory(String directoryPath) {
        return index(documentSplitterService.chunkDirectory(directoryPath));
    }

    public int size() {
        return embeddingStore.size();
    }

    public void clear() {
        embeddingStore.removeAll();
    }
}
