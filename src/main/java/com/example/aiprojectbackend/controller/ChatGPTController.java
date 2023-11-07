package com.example.aiprojectbackend.controller;

import com.example.aiprojectbackend.dto.ChatMessage;
import com.example.aiprojectbackend.dto.ChatRequest;
import com.example.aiprojectbackend.dto.ChatResponse;
import com.example.aiprojectbackend.dto.Choice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ChatGPTController {

    private final WebClient webClient;

    public ChatGPTController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();
    }

    @GetMapping("/chat/{request}")
    public List<Choice> chatWithGPT(@PathVariable String request) {

        System.out.println(request);
        //String request = (String) requestData.get("request");
        //String requestForm = (String) requestData.get("requestValue");

        ChatRequest chatRequest = new ChatRequest(); //ChatRequest objekt har jeg dannet med https://www.jsonschema2pojo.org/ værktøj
        chatRequest.setModel("gpt-3.5-turbo"); //vælg rigtig model. se powerpoint
        List<ChatMessage> listMessages = new ArrayList<>(); //en liste af messages med roller
        listMessages.add(new ChatMessage("system", "You are a helpful assistant."));
        listMessages.add(new ChatMessage("user", request));
        chatRequest.setMessages(listMessages);
        chatRequest.setN(1); //n er antal svar fra chatgpt
        chatRequest.setTemperature(1); //jo højere jo mere fantasifuldt svar (se powerpoint)
        chatRequest.setMaxTokens(50); //længde af svar
        chatRequest.setStream(false); //stream = true, er for viderekomne, der kommer flere svar asynkront
        chatRequest.setPresencePenalty(1); //noget med ikke at gentage sig. se powerpoint

        System.out.println(chatRequest.getMessages());

        ChatResponse response = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .headers(h -> h.setBearerAuth("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"))
                .bodyValue(chatRequest)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();

        List<Choice> list = response.getChoices();
        System.out.println(list);

        return list;
    }


}