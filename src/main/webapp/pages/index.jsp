<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <base href="<%= request.getContextPath() %>/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>도키☆도키☆ 나낭즈</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/home.css">
</head>
<body>
<div id="particles-js" class="particles"></div>

<!-- 오디오 태그 추가 (숨김 처리) -->
<audio id="bgMusic" loop preload="auto" style="display:none;">
    <source src="<%= request.getContextPath() %>/audio/background.mp3" type="audio/mp3">
    <!-- 브라우저가 오디오 태그를 지원하지 않는 경우 대체 텍스트 -->
    브라우저가 오디오 태그를 지원하지 않습니다.
</audio>

<div class="container main-container my-5">
    <div class="row">
        <div class="col-lg-6 d-flex flex-column justify-content-center">
            <div class="text-content">
                <h1 class="game-title">도키☆도키☆ 나낭즈</h1>
                <p class="game-subtitle">호감도를 올려 나낭즈를 공략하세요!</p>
                <div class="btn-wrapper">
                    <a href="<%= request.getContextPath() %>/characterSelection" class="btn start-btn" id="startButton">
                        캐릭터 선택하러 가기 <i class="fas fa-arrow-right ms-2"></i>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-6 text-center">
            <div class="character-container">
                <div class="character character-1" id="character1">
                    <img src="<%= request.getContextPath() %>/api/placeholder/200/300" alt="하나낭" class="character-img">
                    <div class="character-speech-bubble d-none">
                        <p>뭘봐, 너한테 관심 없으니까 꺼져.</p>
                    </div>
                </div>
                <div class="character character-2" id="character2">
                    <img src="<%= request.getContextPath() %>/api/placeholder/200/300" alt="배나낭" class="character-img">
                    <div class="character-speech-bubble d-none">
                        <p>반가워❤ 나는 세상에서 최고로 귀여운 나낭이야! 잘부탁한다낭☆</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="dialog-box" id="dialogBox">
    <div class="dialog-character" id="dialogCharacterName"></div>
    <div class="dialog-text" id="dialogText"></div>
    <div class="dialog-choices" id="dialogChoices">
        <button class="dialog-choice" id="choiceYes"></button>
        <button class="dialog-choice" id="choiceNo"></button>
    </div>
    <div class="dialog-next">
        <i class="fas fa-angle-down"></i>
    </div>
</div>

<button class="music-toggle" id="musicToggle">
    <i class="fas fa-volume-up" id="musicIcon"></i>
</button>

<!-- Define contextPath for JavaScript -->
<script>
    const contextPath = '<%= request.getContextPath() %>';
</script>

<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Particles JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js"></script>
<!-- Custom JS -->
<script src="<%= request.getContextPath() %>/js/home.js"></script>

</body>
</html>