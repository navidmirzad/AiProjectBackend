package com.example.aiprojectbackend.controller;

import com.example.aiprojectbackend.dto.ChatRequest;
import com.example.aiprojectbackend.dto.ChatResponse;
import com.example.aiprojectbackend.dto.Choice;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatGPTController {

    private final WebClient webClient;

    public ChatGPTController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();
    }

    @GetMapping("/chat")
    public List<Choice> chatWithGPT(@RequestParam String message) {
        ChatRequest chatRequest = new ChatRequest(); //ChatRequest objekt har jeg dannet med https://www.jsonschema2pojo.org/ værktøj
        chatRequest.setModel("gpt-3.5-turbo"); //vælg rigtig model. se powerpoint
        List<Message> listMessages = new ArrayList<>(); //en liste af messages med roller
        listMessages.add(new Message("system", "You are a helpful assistant."));
        listMessages.add(new Message("user", "Where is Copenhagen"));
        chatRequest.setMessages(listMessages);
        chatRequest.setN(1); //n er antal svar fra chatgpt
        chatRequest.setTemperature(1); //jo højere jo mere fantasifuldt svar (se powerpoint)
        chatRequest.setMaxTokens(30); //længde af svar
        chatRequest.setStream(false); //stream = true, er for viderekomne, der kommer flere svar asynkront
        chatRequest.setPresencePenalty(1); //noget med ikke at gentage sig. se powerpoint

        ChatResponse response = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .headers(h -> h.setBearerAuth("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"))
                .bodyValue(chatRequest)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();

        List<com.example.aiprojectbackend.dto.Choice> list = response.getChoices();

        return list;

    }
}