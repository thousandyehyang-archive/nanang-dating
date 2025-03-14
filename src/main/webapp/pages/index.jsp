<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/home.css">
</head>
<body>
<div id="particles-js" class="particles"></div>

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
                    <img src="<%= request.getContextPath() %>/images/hana.png" alt="하나낭" class="character-img">
                    <div class="character-speech-bubble d-none">
                        <p>뭘봐, 너한테 관심 없으니까 꺼져.</p>
                    </div>
                </div>
                <div class="character character-2" id="character2">
                    <img src="<%= request.getContextPath() %>/images/bana.png" alt="배나낭" class="character-img">
                    <div class="character-speech-bubble d-none">
                        <p>반가워❤ 나는 세상에서 최고로 귀여운 나낭이야! 잘부탁해~><☆</p>
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

<!-- 음악 토글 버튼 -->
<button class="music-toggle" id="music-toggle">
    <i class="fas fa-volume-mute"></i>
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