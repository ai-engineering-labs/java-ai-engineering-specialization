package br.aritana.javaaiengineeringspecialization.service;

import br.aritana.javaaiengineeringspecialization.document.splitter.DocumentChunkingStrategy;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentSplitterService {

    private final DocumentChunkingStrategy chunkingStrategy;
    private final DocumentLoaderService documentLoaderService;

    public List<TextSegment> chunk(Document document) {
        return chunkingStrategy.chunk(document);
    }

    public List<TextSegment> chunk(List<Document> documents) {
        return documents.stream()
                .flatMap(document -> chunk(document).stream())
                .toList();
    }

    public List<TextSegment> chunkFile(String path) {
        return chunk(documentLoaderService.load(path));
    }

    public List<TextSegment> chunkDirectory(String directoryPath) {
        return chunk(documentLoaderService.loadFromDirectory(directoryPath));
    }
}
