package br.aritana.javaaiengineeringspecialization.document.splitter;

import br.aritana.javaaiengineeringspecialization.config.RagChunkingProperties;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class RecursiveChunkingStrategy implements DocumentChunkingStrategy {

    private final DocumentSplitter splitter;

    public RecursiveChunkingStrategy(RagChunkingProperties properties) {
        this.splitter = DocumentSplitters.recursive(
                properties.getMaxSegmentSize(),
                properties.getMaxOverlapSize()
        );
    }

    @Override
    public List<TextSegment> chunk(Document document) {
        return splitter.split(document);
    }
}
