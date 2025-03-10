package com.nanangdating.service;

import com.nanangdating.config.ConfigManager;
import java.util.*;

public class GroqAIService extends AbstractAIService {
    @Override
    protected String getApiKey() {
        return ConfigManager.GROQ_AI_KEY;
    }

    @Override
    protected String getEndpoint() {
        return ConfigManager.GROQ_AI_ENDPOINT;
    }

    @Override
    protected String getModel() {
        return ConfigManager.GROQ_AI_MODEL;
    }

    @Override
    protected Map<String, Object> buildRequestPayload(String model, String userInput, String characterPrompt) {
        String formattedInput = String.format("%s %s", characterPrompt, userInput);

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", model);

        // AI가 캐릭터 성격을 유지하도록 system 역할 추가
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "너는 특정 캐릭터의 성격을 유지하면서 답변해야 해."));
        messages.add(Map.of("role", "user", "content", formattedInput));

        payload.put("messages", messages);
        payload.put("temperature", 0.7);
        payload.put("max_tokens", 150);
        payload.put("top_p", 1.0);

        return payload;
    }

    @Override
    protected String parseResponse(String response) {
        try {
            Map<String, Object> responseMap = mapper.readValue(response, Map.class);
            if (responseMap.containsKey("error")) {
                return "⚠️ AI 응답 오류: " + responseMap.get("error");
            }

            List<?> choices = (List<?>) responseMap.get("choices");
            if (choices != null && !choices.isEmpty()) {
                Map<?, ?> choice = (Map<?, ?>) choices.get(0);
                Map<?, ?> message = (Map<?, ?>) choice.get("message");
                if (message != null) {
                    return (String) message.get("content");
                }
            }
            return "AI 응답 없음";
        } catch (Exception e) {
            e.printStackTrace();
            return "⚠️ 응답 파싱 오류";
        }
    }
}
