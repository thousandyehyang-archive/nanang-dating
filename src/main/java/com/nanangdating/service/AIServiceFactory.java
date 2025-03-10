package com.nanangdating.service;

import com.nanangdating.model.CharacterModel;

public class AIServiceFactory {
    public static AIService getAIService(CharacterModel character) {
        if (character == null) {
            throw new IllegalArgumentException("⚠️ 캐릭터가 존재하지 않습니다.");
        }

        switch (character.getAiType()) {
            case "groq":
                return new GroqAIService();
            case "together":
                return new TogetherAIService();
            default:
                throw new IllegalArgumentException("⚠️ 지원되지 않는 AI 타입: " + character.getAiType());
        }
    }
}
