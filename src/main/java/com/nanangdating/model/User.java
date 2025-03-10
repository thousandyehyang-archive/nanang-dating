package com.nanangdating.model;

public class User {
    private String username;
    private int affinity; // ✅ 사용자 호감도 저장

    public User() {
        this.username = "anonymous";
        this.affinity = 25; // 기본 호감도
    }

    public User(String username, int affinity) {
        this.username = username;
        this.affinity = affinity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAffinity() {
        return affinity;
    }

    public void setAffinity(int affinity) {
        this.affinity = Math.max(0, Math.min(100, affinity)); // 0~100 제한
    }
}
