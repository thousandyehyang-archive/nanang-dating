package com.nanangdating.model;

public class GameStatus {
    private boolean success;
    private int affinity;
    private String message;

    public GameStatus() {}

    public GameStatus(boolean success, int affinity, String message) {
        this.success = success;
        this.affinity = affinity;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public int getAffinity() {
        return affinity;
    }
    public void setAffinity(int affinity) {
        this.affinity = affinity;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
