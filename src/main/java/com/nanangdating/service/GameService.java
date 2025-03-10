package com.nanangdating.service;

import com.nanangdating.model.GameStatus;
import com.nanangdating.model.User;

public class GameService {
    // 예시로 초기 호감도는 25로 설정합니다.
    private int affinity = 25;
    private AIApiService aiApiService = new AIApiService();

    public GameStatus processGame(String characterId, User user, String userInput) {
        // 사용자 입력에 따라 호감도 변경 로직
        if (userInput.contains("좋아") || userInput.contains("사랑")) {
            affinity += 10;
        } else if (userInput.contains("싫어") || userInput.contains("미워")) {
            affinity -= 10;
        }
        // 호감도를 0 ~ 100 범위로 제한
        if (affinity < 0) affinity = 0;
        if (affinity > 100) affinity = 100;

        // 캐릭터 id에 따라 서로 다른 AI API 호출
        String aiResponse;
        if ("1".equals(characterId)) {
            aiResponse = aiApiService.getGroqAiResponse(userInput);
        } else if ("2".equals(characterId)) {
            aiResponse = aiApiService.getTogetherAiResponse(userInput);
        } else {
            aiResponse = "알 수 없는 캐릭터입니다.";
        }

        // AI 응답과 업데이트된 호감도를 조합하여 메시지 생성
        String message = aiResponse + " | 현재 호감도: " + affinity;
        return new GameStatus(true, affinity, message);
    }
}
