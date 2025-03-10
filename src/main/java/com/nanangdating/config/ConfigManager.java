package com.nanangdating.config;

public class ConfigManager {
    public static final String TOGETHER_AI_KEY = System.getenv("TOGETHER_AI_KEY");
    public static final String GROQ_AI_KEY = System.getenv("GROQ_API_KEY");

    public static final String TOGETHER_AI_MODEL = System.getenv("TOGETHER_AI_MODEL");
    public static final String GROQ_AI_MODEL = System.getenv("GROQ_AI_MODEL");

    public static final String TOGETHER_AI_ENDPOINT = System.getenv("TOGETHER_AI_ENDPOINT");
    public static final String GROQ_AI_ENDPOINT = System.getenv("GROQ_AI_ENDPOINT");

    // 🟡 캐릭터별 프롬프팅을 환경변수에서 불러오기
    public static final String PROMPT_HANA = System.getenv("PROMPT_HANA");
    public static final String PROMPT_BANA = System.getenv("PROMPT_BANA");

    public static boolean isConfigValid() {
        return TOGETHER_AI_KEY != null && GROQ_AI_KEY != null &&
                TOGETHER_AI_MODEL != null && GROQ_AI_MODEL != null &&
                TOGETHER_AI_ENDPOINT != null && GROQ_AI_ENDPOINT != null &&
                PROMPT_BANA != null && PROMPT_HANA != null;
    }
}
