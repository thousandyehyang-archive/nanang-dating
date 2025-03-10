package com.nanangdating.service;

import com.nanangdating.model.CharacterModel;
import com.nanangdating.model.GameStatus;
import com.nanangdating.model.User;

public class GameService {
    private int affinity = 25;
    private final CharacterService characterService = new CharacterService();

    public GameStatus processGame(String characterId, User user, String userInput) {
        CharacterModel character = characterService.getCharacterById(characterId);

        if (character == null) {
            return new GameStatus(false, affinity, "⚠️ 존재하지 않는 캐릭터입니다.");
        }

        AIService aiService = AIServiceFactory.getAIService(character);
        String aiResponse = aiService.getResponse(userInput, character.getAiPrompt()); // ✅ 캐릭터 프롬프팅 적용

        if (userInput.contains("좋아") || userInput.contains("사랑")) {
            affinity += 10;
        } else if (userInput.contains("싫어") || userInput.contains("미워")) {
            affinity -= 10;
        }

        affinity = Math.max(0, Math.min(100, affinity)); // 0~100 범위 제한

        String message = aiResponse + " | 현재 호감도: " + affinity;
        return new GameStatus(true, affinity, message);
    }
}
