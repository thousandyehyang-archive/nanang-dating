package com.nanangdating.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanangdating.util.ApiClient;

import java.util.*;

public abstract class AbstractAIService implements AIService {
    protected final ObjectMapper mapper = new ObjectMapper();

    /**
     * AI 요청을 위한 공통 메서드.
     */
    protected String sendRequest(String apiKey, String endpoint, String model, String userInput, String characterPrompt) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "⚠️ API 키가 설정되지 않았습니다.";
        }

        String formattedInput = String.format("%s %s", characterPrompt, userInput); // ✅ 프롬프팅 적용

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", model);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", formattedInput));

        payload.put("messages", messages);

        String jsonPayload;
        try {
            jsonPayload = mapper.writeValueAsString(payload);
        } catch (Exception e) {
            e.printStackTrace();
            return "Payload 생성 오류";
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiKey);
        headers.put("Content-Type", "application/json");

        System.out.println("📌 AI 요청: " + jsonPayload);
        String response = ApiClient.sendPostRequest(endpoint, jsonPayload, headers);
        System.out.println("📌 AI 응답: " + response);

        return extractAIResponse(response);
    }

    /**
     * AI 응답 JSON에서 원하는 메시지를 추출하는 메서드.
     */
    protected String extractAIResponse(String response) {
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
