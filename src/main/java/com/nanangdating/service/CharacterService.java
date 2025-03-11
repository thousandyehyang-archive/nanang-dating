package com.nanangdating.service;

import com.nanangdating.config.ConfigManager;
import com.nanangdating.model.CharacterModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CharacterService {
    private final List<CharacterModel> characters;

    public CharacterService() {
        characters = new ArrayList<>();

        // 🟡 Groq AI 사용 캐릭터
        characters.add(new CharacterModel(
                "1",
                "하나낭",
                "나는 사람한텐 관심 없어. 오로지 개발뿐이라고, 흥",
                "hana.png",
                25,
                "groq",
                ConfigManager.PROMPT_HANA
        ));

        // 🔵 Together AI 사용 캐릭터 (하나)
        characters.add(new CharacterModel(
                "2",
                "배나낭",
                "안녕! 좋은 아침이이야! 너는 어땠어?",
                "bana.png",
                30,
                "together",
                ConfigManager.PROMPT_BANA
        ));
    }

    public List<CharacterModel> getAvailableCharacters() {
        return characters;
    }

    public CharacterModel getCharacterById(String characterId) {
        Optional<CharacterModel> character = characters.stream()
                .filter(c -> c.getId().equals(characterId))
                .findFirst();
        return character.orElse(null);
    }
}
