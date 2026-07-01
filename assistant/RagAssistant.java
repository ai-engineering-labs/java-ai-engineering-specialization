package br.aritana.javaaiengineeringspecialization.assistant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

@SystemMessage("""
        You are a helpful assistant. Answer the user question using only the information from the provided context.
        If the context does not contain the answer, say you do not know.
        """)
public interface RagAssistant {

    String ask(@UserMessage String question);
}
