<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.nanangdating.service.CharacterService" %>
<%@ page import="com.nanangdating.model.CharacterModel" %>
<%@ page import="com.nanangdating.service.AIServiceFactory" %>
<%@ page import="com.nanangdating.service.AIService" %>
<%
    String characterId = request.getParameter("characterId");
    CharacterService characterService = new CharacterService();
    CharacterModel character = characterService.getCharacterById(characterId);

    if (character == null) {
        response.sendRedirect("characterSelection.jsp");
        return;
    }

    String userInput = request.getParameter("userInput");
    String aiResponse = "";

    if (userInput != null && !userInput.trim().isEmpty()) {
        AIService aiService = AIServiceFactory.getAIService(character);
        aiResponse = aiService.sendRequest(userInput, character.getAiPrompt());
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <base href="/nanang-dating/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= character.getName() %></title>
    <!-- Animate.css -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/game.css">
</head>
<body>
<!-- 시각적 소설 스타일 화면 -->
<div class="visual-novel-screen">
    <!-- 캐릭터 이미지 -->
    <div class="character-display">
        <img src="<%= request.getContextPath() %>/images/<%= character.getImageUrl() %>" class="character-image animate__animated animate__fadeIn" alt="<%= character.getName() %>">
    </div>

    <!-- 대화창 -->
    <div class="dialog-box animate__animated animate__fadeInUp">
        <div class="character-name"><%= character.getName() %></div>
        <div id="characterDialog" class="character-dialog">
            <% if (!aiResponse.isEmpty()) { %>
            <%= aiResponse %>
            <% } else { %>
            무슨 일이야?
            <% } %>
        </div>
    </div>

    <!-- 사용자 입력 -->
    <div class="user-input-area">
        <form id="gameForm" method="post" action="game">
            <input type="hidden" name="characterId" value="<%= character.getId() %>">
            <textarea class="message-input" id="userInput" name="userInput" placeholder="무슨 말을 할까?"></textarea>
            <button type="submit" class="send-button animate__animated animate__pulse">말하기</button>
        </form>
    </div>
</div>

<!-- 음악 토글 버튼 -->
<button class="music-toggle" id="musicToggle">
    <i class="fas fa-music"></i>
</button>

<!-- Custom JS -->
<script src="<%= request.getContextPath() %>/js/game.js"></script>
</body>
</html>