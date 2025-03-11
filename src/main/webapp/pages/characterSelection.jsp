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
    <base href="<%= request.getContextPath() %>/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>도키☆도키☆ 나낭즈</title>

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
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/character.css">
</head>
<body>
<!-- 파티클 효과 - 가장 먼저 배치하여 배경이 되도록 함 -->
<div id="particles-js"></div>

<!-- 메인 컨텐츠 -->
<div class="container main-container">
    <div class="row characters-row">
        <% for (int i = 0; i < characters.size(); i++) { %>
        <% CharacterModel character = characters.get(i); %>
        <div class="col-md-4 character-column">
            <div class="character-card" id="character-<%= character.getId() %>">
                <div class="character-img-container">
                    <img src="<%= request.getContextPath() %>/images/<%= character.getImageUrl() %>" class="character-img" alt="<%= character.getName() %>">
                    <div class="character-overlay"></div>
                </div>
                <div class="card-body p-4">
                    <h3 class="character-name"><%= character.getName() %></h3>
                    <p class="character-description"><%= character.getDescription() %></p>

                    <div class="text-center">
                        <a href="<%= request.getContextPath() %>/game?characterId=<%= character.getId() %>" class="select-btn">선택하기</a>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<div class="dialog-box">
    <div class="dialog-character">운영자</div>
    <div class="dialog-text">공략할 캐릭터를 선택해주세요!</div>
    <div class="dialog-next">
        <i class="fas fa-angle-down"></i>
    </div>
</div>

<!-- 음악 토글 버튼 -->
<button class="music-toggle" id="music-toggle">
    <i class="fas fa-volume-mute"></i>
</button>

<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Particles.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js"></script>
<!-- Custom JS -->
<script src="<%= request.getContextPath() %>/js/character.js"></script>
</body>
</html>