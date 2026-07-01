package br.aritana.javaaiengineeringspecialization.document;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import org.springframework.stereotype.Component;

@Component
public class TxtDocumentLoader implements DocumentLoader {

    @Override
    public boolean supports(String fileName) {
        return fileName.toLowerCase().endsWith(".txt");
    }

    @Override
    public Document load(String path) {
        return FileSystemDocumentLoader.loadDocument(path, new TextDocumentParser());
    }
}
