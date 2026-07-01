package br.aritana.javaaiengineeringspecialization.document;

import dev.langchain4j.data.document.Document;

public interface DocumentLoader {
    boolean supports(String fileName);
    Document load(String path);
}
