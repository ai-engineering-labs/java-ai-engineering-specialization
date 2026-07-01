package br.aritana.javaaiengineeringspecialization.service;

import br.aritana.javaaiengineeringspecialization.document.DocumentLoader;
import dev.langchain4j.data.document.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DocumentLoaderService {

    private final List<DocumentLoader> loaders;

    public Document load(String path) {
        Path filePath = Path.of(path);

        if (!Files.isRegularFile(filePath)) {
            throw new IllegalArgumentException("File not found: " + path);
        }

        String fileName = filePath.getFileName().toString();

        return loaders.stream()
                .filter(loader -> loader.supports(fileName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported file type: " + fileName))
                .load(path);
    }

    public List<Document> loadFromDirectory(String directoryPath) {
        Path directory = Path.of(directoryPath);

        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Directory not found: " + directoryPath);
        }

        try (Stream<Path> files = Files.list(directory)) {
            return files
                    .filter(Files::isRegularFile)
                    .map(path -> load(path.toString()))
                    .toList();
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read directory: " + directoryPath, e);
        }
    }
}
