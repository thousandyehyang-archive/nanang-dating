package com.nanangdating.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class ConfigManager {
    public static final String TOGETHER_AI_KEY = System.getenv("TOGETHER_AI_KEY");
    public static final String GROQ_AI_KEY = System.getenv("GROQ_API_KEY");

    public static final String TOGETHER_AI_MODEL = System.getenv("TOGETHER_AI_MODEL");
    public static final String GROQ_AI_MODEL = System.getenv("GROQ_AI_MODEL");

    public static final String TOGETHER_AI_ENDPOINT = System.getenv("TOGETHER_AI_ENDPOINT");
    public static final String GROQ_AI_ENDPOINT = System.getenv("GROQ_AI_ENDPOINT");

    public static final String PROMPT_HANA = loadPrompt("prompts/hana_prompt.txt");
    public static final String PROMPT_BANA = loadPrompt("prompts/bana_prompt.txt");

    private static String loadPrompt(String filePath) {
        try {
            return new String(Files.readAllBytes(
                    Paths.get(Objects.requireNonNull(
                            ConfigManager.class.getClassLoader().getResource(filePath)
                    ).toURI())), StandardCharsets.UTF_8);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("프롬프트 파일 로딩 실패: " + filePath, e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isConfigValid() {
        return TOGETHER_AI_KEY != null && GROQ_AI_KEY != null &&
                TOGETHER_AI_MODEL != null && GROQ_AI_MODEL != null &&
                TOGETHER_AI_ENDPOINT != null && GROQ_AI_ENDPOINT != null;
    }
}
