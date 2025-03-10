package com.nanangdating.service;

public interface AIService {
    /**
     * AI 모델에 요청을 보내고 응답을 받는 메서드
     *
     * @param userInput       사용자 입력 메시지
     * @param characterPrompt 캐릭터별 프롬프팅 텍스트
     * @return AI의 응답 메시지
     */
    String sendRequest(String userInput, String characterPrompt);
}
