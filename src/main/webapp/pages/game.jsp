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
        aiResponse = aiService.sendRequest(userInput, character.getAiPrompt()); // ✅ 메서드 수정
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <base href="/nanang-dating/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>게임 진행 - <%= character.getName() %></title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #222222;">
    <div class="container">
        <a class="navbar-brand" href="characterSelection">나낭 데이팅</a>
    </div>
</nav>

<div class="container my-5">
    <h1 class="text-center mb-4" style="color: #FFD700;"><%= character.getName() %>과의 대화</h1>
    <div class="card" style="background-color: #333333; color: #fff;">
        <div class="card-body">
            <div id="conversation">
                <% if (!aiResponse.isEmpty()) { %>
                <p><strong><%= character.getName() %>:</strong> <%= aiResponse %></p>
                <% } %>
            </div>
            <form id="gameForm" method="post" action="game">
                <input type="hidden" name="characterId" value="<%= character.getId() %>">
                <div class="mb-3">
                    <label for="userInput" class="form-label">메시지 입력</label>
                    <textarea class="form-control" id="userInput" name="userInput" rows="3" placeholder="메시지를 입력하세요"></textarea>
                </div>
                <button type="submit" class="btn btn-lg" style="background-color: #FFD700; color: #222222;">전송</button>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
