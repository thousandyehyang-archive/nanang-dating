package com.nanangdating.model;

public class CharacterModel {
    private String id;
    private String name;
    private String description;
    private String imagePath;
    private int affinity; // 초기 호감도

    public CharacterModel() {}

    public CharacterModel(String id, String name, String description, String imagePath, int affinity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.affinity = affinity;
    }

    // Getter & Setter 메서드
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public int getAffinity() {
        return affinity;
    }
    public void setAffinity(int affinity) {
        this.affinity = affinity;
    }
}