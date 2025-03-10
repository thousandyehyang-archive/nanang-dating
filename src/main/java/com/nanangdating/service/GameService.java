package com.nanangdating.service;

import com.nanangdating.model.CharacterModel;
import com.nanangdating.model.GameStatus;
import com.nanangdating.model.User;

public class GameService {
    private final CharacterService characterService = new CharacterService();

    /**
     * 게임 진행을 처리하는 메서드
     *
     * @param characterId 캐릭터 ID
     * @param user        사용자 객체
     * @param userInput   사용자의 입력 메시지
     * @return 게임 상태(GameStatus) 객체
     */
    public GameStatus processGame(String characterId, User user, String userInput) {
        CharacterModel character = characterService.getCharacterById(characterId);

        if (character == null) {
            return new GameStatus(false, user.getAffinity(), "⚠️ 존재하지 않는 캐릭터입니다.");
        }

        AIService aiService = AIServiceFactory.getAIService(character);
        String aiResponse = aiService.sendRequest(userInput, character.getAiPrompt()); // ✅ 캐릭터별 프롬프팅 적용

        // 🎯 호감도 업데이트
        int updatedAffinity = updateAffinity(user.getAffinity(), userInput);
        user.setAffinity(updatedAffinity);

        // 🎯 최종 응답 메시지 생성
        String message = String.format("%s | 현재 호감도: %d", aiResponse, updatedAffinity);

        return new GameStatus(true, updatedAffinity, message);
    }

    /**
     * 사용자 입력에 따라 호감도를 조정하는 메서드
     *
     * @param currentAffinity 현재 호감도
     * @param userInput       사용자 입력 메시지
     * @return 업데이트된 호감도
     */
    private int updateAffinity(int currentAffinity, String userInput) {
        int affinityChange = 0;

        if (userInput.contains("좋아") || userInput.contains("사랑")) {
            affinityChange += 10;
        } else if (userInput.contains("싫어") || userInput.contains("미워")) {
            affinityChange -= 10;
        }

        // 0~100 사이로 제한
        return Math.max(0, Math.min(100, currentAffinity + affinityChange));
    }
}
