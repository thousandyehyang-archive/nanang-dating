package com.nanangdating.service;

import com.nanangdating.config.ConfigManager;

public class GroqAIService extends AbstractAIService {
    @Override
    public String getResponse(String userInput, String characterPrompt) {
        return sendRequest(
                ConfigManager.GROQ_AI_KEY,
                ConfigManager.GROQ_AI_ENDPOINT,
                ConfigManager.GROQ_AI_MODEL,
                userInput,
                characterPrompt
        );
    }
}
