package com.nanangdating.service;

import com.nanangdating.config.ConfigManager;

public class TogetherAIService extends AbstractAIService {
    @Override
    public String getResponse(String userInput, String characterPrompt) {
        return sendRequest(
                ConfigManager.TOGETHER_AI_KEY,
                ConfigManager.TOGETHER_AI_ENDPOINT,
                ConfigManager.TOGETHER_AI_MODEL,
                userInput,
                characterPrompt
        );
    }
}
