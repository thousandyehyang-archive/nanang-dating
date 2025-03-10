package com.nanangdating.service;

import com.nanangdating.model.CharacterModel;
import java.util.ArrayList;
import java.util.List;

public class CharacterService {
    public List<CharacterModel> getAvailableCharacters() {
        List<CharacterModel> characters = new ArrayList<>();
        // 예시 캐릭터: 나낭
        characters.add(new CharacterModel("1", "나낭", "미연시 공략 대상 캐릭터", "/images/nanang.png", 25));
        // 필요시 추가 캐릭터를 넣을 수 있음
        return characters;
    }
}