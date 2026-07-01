package br.aritana.javaaiengineeringspecialization.service;

import br.aritana.javaaiengineeringspecialization.assistant.RagAssistant;
import dev.langchain4j.data.segment.TextSegment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RagQueryService {

    private final RagAssistant ragAssistant;
    private final DocumentRetrievalService documentRetrievalService;

    public String ask(String question) {
        return ragAssistant.ask(question);
    }

    public List<TextSegment> findRelevantChunks(String question) {
        return documentRetrievalService.retrieve(question);
    }
}
