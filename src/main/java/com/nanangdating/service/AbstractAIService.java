package com.nanangdating.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanangdating.util.ApiClient;
import java.util.*;

public abstract class AbstractAIService implements AIService {
    protected final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String sendRequest(String userInput, String characterPrompt) {
        // API 엔드포인트, 모델 및 키를 서브클래스에서 가져옴
        String apiKey = getApiKey();
        String endpoint = getEndpoint();
        String model = getModel();

        if (apiKey == null || apiKey.isEmpty()) {
            return "⚠️ API 키가 설정되지 않았습니다.";
        }

        // AI 모델별 JSON 요청 포맷 생성 (서브클래스에서 구현)
        Map<String, Object> payload = buildRequestPayload(model, userInput, characterPrompt);

        String jsonPayload;
        try {
            jsonPayload = mapper.writeValueAsString(payload);
        } catch (Exception e) {
            e.printStackTrace();
            return "⚠️ Payload 생성 오류";
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiKey);
        headers.put("Content-Type", "application/json");

        System.out.println("📌 AI 요청: " + jsonPayload);
        String response = ApiClient.sendPostRequest(endpoint, jsonPayload, headers);
        System.out.println("📌 AI 응답: " + response);

        return parseResponse(response);
    }

    /**
     * API 키를 반환하는 메서드 (서브클래스에서 구현)
     */
    protected abstract String getApiKey();

    /**
     * API 엔드포인트를 반환하는 메서드 (서브클래스에서 구현)
     */
    protected abstract String getEndpoint();

    /**
     * AI 모델명을 반환하는 메서드 (서브클래스에서 구현)
     */
    protected abstract String getModel();

    /**
     * 모델별 요청 JSON 생성 (서브클래스에서 구현)
     */
    protected abstract Map<String, Object> buildRequestPayload(String model, String userInput, String characterPrompt);

    /**
     * 모델별 응답 JSON 파싱 (서브클래스에서 구현)
     */
    protected abstract String parseResponse(String response);
}
