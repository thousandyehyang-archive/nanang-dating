package com.nanangdating.model;

public class CharacterModel {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private int initialAffinity;
    private String aiType; // "groq" 또는 "together"
    private String aiPrompt; // 캐릭터별 프롬프팅 설정

    public CharacterModel(String id, String name, String description, String imageUrl, int initialAffinity, String aiType, String aiPrompt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.initialAffinity = initialAffinity;
        this.aiType = aiType;
        this.aiPrompt = aiPrompt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getInitialAffinity() {
        return initialAffinity;
    }

    public String getAiType() {
        return aiType;
    }

    public String getAiPrompt() {
        return aiPrompt;
    }
}
