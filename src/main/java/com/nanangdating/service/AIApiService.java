package com.nanangdating.service;

import com.nanangdating.util.ApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AIApiService {
    // 환경변수를 통해 API 키를 불러옵니다.
    private final String togetherAiKey = System.getenv("TOGETHER_AI_KEY");
    private final String groqKey = System.getenv("GROQ_API_KEY");

    private final String togetherAiModel = "Llama 3.3 70B Instruct Turbo Free";
    private final String groqAiModel = "llama-3.3-70b-versatile";

    // 실제 API 엔드포인트 URL
    private final String togetherAiEndpoint = "https://api.together.ai/v1/generate";
    private final String groqAiEndpoint = "https://api.groq.com/openai/v1/chat/completions";

    private ObjectMapper mapper = new ObjectMapper();

    // 사용자 입력 앞에 프롬프트를 추가하는 메서드
    private String addCustomPrompt(String userInput) {
        // 원하는 스타일에 맞게 프롬프트를 수정합니다.
        String promptPrefix = "당신은 진짜 여고생처럼 귀엽고 자연스러운 말투로 답변해줘. ";
        return promptPrefix + userInput;
    }

    public String getTogetherAiResponse(String input) {
        // 프롬프트 커스터마이징 적용
        String finalInput = addCustomPrompt(input);

        if ("안녕?".equals(input.trim())) {
            return "안녕하세요! 저는 Together AI입니다.";
        }
        String payload = "{\"model\":\"" + togetherAiModel + "\", \"prompt\":\"" + finalInput + "\"}";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + togetherAiKey);
        headers.put("Content-Type", "application/json");

        String response = ApiClient.sendPostRequest(togetherAiEndpoint, payload, headers);
        try {
            Map<String, Object> map = mapper.readValue(response, Map.class);
            if (map.containsKey("error")) {
                System.err.println("together AI error: " + map.get("error"));
                return "AI 응답을 받아오지 못했습니다.";
            }
            Object reply = map.get("reply");
            return reply != null ? reply.toString() : "AI 응답 없음";
        } catch (Exception e) {
            e.printStackTrace();
            return "응답 파싱 중 오류 발생";
        }
    }

    public String getGroqAiResponse(String input) {
        // 프롬프트 커스터마이징 적용
        String finalInput = addCustomPrompt(input);

        if ("안녕?".equals(input.trim())) {
            return "안녕하세요! 저는 Groq AI입니다.";
        }
        // Groq API는 messages 배열 구조를 사용합니다.
        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("model", groqAiModel);
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", finalInput);
        messages.add(message);
        payloadMap.put("messages", messages);

        String payload;
        try {
            payload = mapper.writeValueAsString(payloadMap);
        } catch(Exception e) {
            e.printStackTrace();
            return "Payload 생성 오류";
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + groqKey);
        headers.put("Content-Type", "application/json");

        System.out.println("Groq payload: " + payload);
        String response = ApiClient.sendPostRequest(groqAiEndpoint, payload, headers);
        System.out.println("Groq raw response: " + response);
        try {
            Map<String, Object> map = mapper.readValue(response, Map.class);
            if (map.containsKey("error")) {
                System.err.println("groq AI error: " + map.get("error"));
                return "AI 응답을 받아오지 못했습니다.";
            }
            Object choicesObj = map.get("choices");
            if (choicesObj instanceof List) {
                List<?> choices = (List<?>) choicesObj;
                if (!choices.isEmpty()) {
                    Object firstChoice = choices.get(0);
                    if (firstChoice instanceof Map) {
                        Map<?, ?> choiceMap = (Map<?, ?>) firstChoice;
                        Object messageObj = choiceMap.get("message");
                        if (messageObj instanceof Map) {
                            Map<?, ?> messageMap = (Map<?, ?>) messageObj;
                            Object content = messageMap.get("content");
                            return content != null ? content.toString() : "AI 응답 없음";
                        }
                    }
                }
            }
            return "AI 응답 없음";
        } catch (Exception e) {
            e.printStackTrace();
            return "응답 파싱 중 오류 발생";
        }
    }
}
