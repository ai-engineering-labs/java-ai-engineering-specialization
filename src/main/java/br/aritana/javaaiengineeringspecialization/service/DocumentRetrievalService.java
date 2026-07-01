package br.aritana.javaaiengineeringspecialization.service;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentRetrievalService {

    private final ContentRetriever contentRetriever;

    public List<TextSegment> retrieve(String question) {
        return contentRetriever.retrieve(Query.from(question)).stream()
                .map(content -> content.textSegment())
                .toList();
    }
}
