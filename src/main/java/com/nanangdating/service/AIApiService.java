package com.nanangdating.service;

import com.nanangdating.util.ApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class AIApiService {
    private static final String TOGETHER_AI_KEY = System.getenv("TOGETHER_AI_KEY");
    private static final String GROQ_API_KEY = System.getenv("GROQ_API_KEY");

    private static final String TOGETHER_AI_MODEL = System.getenv("TOGETHER_AI_MODEL");
    private static final String GROQ_AI_MODEL = System.getenv("GROQ_AI_MODEL");

    private static final String TOGETHER_AI_ENDPOINT = System.getenv("TOGETHER_AI_ENDPOINT");
    private static final String GROQ_AI_ENDPOINT = System.getenv("GROQ_AI_ENDPOINT");

    private static final String PROMPT_PREFIX = System.getenv("GROQ_AI_PROMPT");

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 프롬프트에 사용자 입력을 추가하여 AI 모델이 원하는 스타일로 응답하도록 유도하는 메서드.
     */
    private String formatPrompt(String userInput) {
        return String.format("%s %s", PROMPT_PREFIX, userInput);
    }

    /**
     * AI 모델에게 요청을 보내고 응답을 받는 공통 메서드.
     */
    private String sendRequestToAI(String apiKey, String endpoint, String model, String userInput) {
        String formattedInput = formatPrompt(userInput);

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", model);

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", formattedInput);
        messages.add(message);

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

        System.out.println("AI 요청 payload: " + jsonPayload);

        String response = ApiClient.sendPostRequest(endpoint, jsonPayload, headers);
        System.out.println("AI 응답: " + response);

        return extractAIResponse(response);
    }

    /**
     * Groq API를 호출하는 메서드.
     */
    public String getGroqAiResponse(String userInput) {
        return sendRequestToAI(GROQ_API_KEY, GROQ_AI_ENDPOINT, GROQ_AI_MODEL, userInput);
    }

    /**
     * Together AI API를 호출하는 메서드.
     */
    public String getTogetherAiResponse(String userInput) {
        return sendRequestToAI(TOGETHER_AI_KEY, TOGETHER_AI_ENDPOINT, TOGETHER_AI_MODEL, userInput);
    }

    /**
     * AI 응답 JSON에서 원하는 메시지를 추출하는 메서드.
     */
    private String extractAIResponse(String response) {
        try {
            Map<String, Object> responseMap = mapper.readValue(response, Map.class);
            if (responseMap.containsKey("error")) {
                System.err.println("AI 응답 오류: " + responseMap.get("error"));
                return "AI 응답을 받아오지 못했습니다.";
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
            return "응답 파싱 중 오류 발생";
        }
    }
}
