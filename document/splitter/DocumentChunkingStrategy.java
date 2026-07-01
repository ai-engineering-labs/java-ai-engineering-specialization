package br.aritana.javaaiengineeringspecialization.document.splitter;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;

import java.util.List;

public interface DocumentChunkingStrategy {

    List<TextSegment> chunk(Document document);
}
