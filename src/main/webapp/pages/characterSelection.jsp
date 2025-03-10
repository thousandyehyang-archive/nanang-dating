<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.nanangdating.service.CharacterService" %>
<%@ page import="com.nanangdating.model.CharacterModel" %>
<%@ page import="java.util.List" %>
<%
    CharacterService characterService = new CharacterService();
    List<CharacterModel> characters = characterService.getAvailableCharacters();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <base href="/nanang-dating/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>캐릭터 선택 - 나낭 데이팅</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #222222;">
    <div class="container">
        <a class="navbar-brand" href="#">나낭 데이팅</a>
    </div>
</nav>

<div class="container my-5">
    <h1 class="text-center mb-4" style="color: #FFD700;">캐릭터 선택</h1>
    <div class="row">
        <% for (CharacterModel character : characters) { %>
        <div class="col-md-4">
            <div class="card mb-4" style="background-color: #333333; border: none;">
                <img src="<%= character.getImageUrl() %>" class="card-img-top" alt="<%= character.getName() %>">
                <div class="card-body text-center">
                    <h5 class="card-title" style="color: #FFD700;"><%= character.getName() %></h5>
                    <p class="card-text"><%= character.getDescription() %></p>
                    <a href="game?characterId=<%= character.getId() %>" class="btn" style="background-color: #FFD700; color: #222222;">선택하기</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Custom JS -->
<script src="../js/script.js"></script>
</body>
</html>