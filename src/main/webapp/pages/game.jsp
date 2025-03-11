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
    <base href="<%= request.getContextPath() %>/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= character.getName() %></title>

    <!-- Favicon -->
    <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/favicon-96x96.png" sizes="96x96">
    <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/favicon.svg">
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/favicon.ico">
    <link rel="apple-touch-icon" sizes="180x180" href="<%= request.getContextPath() %>/images/apple-touch-icon.png">
    <meta name="apple-mobile-web-app-title" content="ドキ☆ドキ☆ナナンズ">
    <link rel="manifest" href="<%= request.getContextPath() %>/site.webmanifest">

    <!-- Open Graph Meta Tags -->
    <meta property="og:title" content="도키☆도키☆ 나낭즈">
    <meta property="og:description" content="호감도를 올려 나낭즈를 공략하세요!">
    <meta property="og:image" content="<%= request.getContextPath() %>/images/doki-doki.png">
    <meta property="og:url" content="https://nanang-dating.onrender.com">
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="도키☆도키☆ 나낭즈">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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
<button class="music-toggle" id="music-toggle">
    <i class="fas fa-volume-mute"></i>
</button>

<!-- Custom JS -->
<script src="<%= request.getContextPath() %>/js/game.js"></script>
</body>
</html>