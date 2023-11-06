package com.example.aiprojectbackend.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.logging.log4j.message.Message;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "model",
        "messages",
        "n",
        "temperature",
        "max_tokens",
        "stream",
        "presence_penalty"
})
@Generated("jsonschema2pojo")
public class ChatRequest {

    @JsonProperty("model")
    private String model;
    @JsonProperty("messages")
    private List<ChatMessage> messages;
    @JsonProperty("n")
    private Integer n;
    @JsonProperty("temperature")
    private Integer temperature;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    @JsonProperty("stream")
    private Boolean stream;
    @JsonProperty("presence_penalty")
    private Integer presencePenalty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("messages")
    public List<ChatMessage> getMessages() {
        return messages;
    }

    @JsonProperty("messages")
    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @JsonProperty("n")
    public Integer getN() {
        return n;
    }

    @JsonProperty("n")
    public void setN(Integer n) {
        this.n = n;
    }

    @JsonProperty("temperature")
    public Integer getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("max_tokens")
    public Integer getMaxTokens() {
        return maxTokens;
    }

    @JsonProperty("max_tokens")
    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    @JsonProperty("stream")
    public Boolean getStream() {
        return stream;
    }

    @JsonProperty("stream")
    public void setStream(Boolean stream) {
        this.stream = stream;
    }

    @JsonProperty("presence_penalty")
    public Integer getPresencePenalty() {
        return presencePenalty;
    }

    @JsonProperty("presence_penalty")
    public void setPresencePenalty(Integer presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
