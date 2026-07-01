package br.aritana.javaaiengineeringspecialization.controller;

import br.aritana.javaaiengineeringspecialization.service.DocumentIndexingService;
import br.aritana.javaaiengineeringspecialization.service.RagQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Tag(name = "RAG", description = "Indexação e consulta com Retrieval-Augmented Generation")
@RestController
@RequestMapping("/rag")
public class RagController {

    private final RagQueryService ragQueryService;
    private final DocumentIndexingService documentIndexingService;

    public RagController(RagQueryService ragQueryService, DocumentIndexingService documentIndexingService) {
        this.ragQueryService = ragQueryService;
        this.documentIndexingService = documentIndexingService;
    }

    @Operation(summary = "Indexa um arquivo do filesystem")
    @PostMapping(value = "/index/file", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Map<String, Integer> indexFile(@RequestBody String path) {
        int indexed = documentIndexingService.indexFile(path);
        return Map.of("indexedSegments", indexed);
    }

    @Operation(summary = "Indexa todos os arquivos suportados de um diretório")
    @PostMapping(value = "/index/directory", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Map<String, Integer> indexDirectory(@RequestBody String directoryPath) {
        int indexed = documentIndexingService.indexDirectory(directoryPath);
        return Map.of("indexedSegments", indexed);
    }

    @Operation(summary = "Recupera chunks relevantes para uma pergunta")
    @PostMapping(
            value = "/retrieve",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<String> retrieve(@RequestBody String question) {
        return ragQueryService.findRelevantChunks(question).stream()
                .map(segment -> segment.text())
                .toList();
    }

    @Operation(summary = "Faz uma pergunta com RAG e retorna a resposta do LLM")
    @PostMapping(
            value = "/ask",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String ask(@RequestBody String question) {
        return ragQueryService.ask(question);
    }
}
