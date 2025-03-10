<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <base href="/nanang-dating/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>게임 진행 - 나낭 데이팅</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #222222;">
    <div class="container">
        <a class="navbar-brand" href="index.html">나낭 데이팅</a>
    </div>
</nav>

<div class="container my-5">
    <h1 class="text-center mb-4" style="color: #FFD700;">게임 진행</h1>
    <div class="card" style="background-color: #333333; color: #fff;">
        <div class="card-body">
            <div id="conversation">
                <!-- AI 응답과 현재 호감도 메시지 출력 -->
                <p>${gameStatus.message}</p>
            </div>
            <form id="gameForm" method="post" action="game">
                <input type="hidden" name="characterId" value="1"> <!-- 1 또는 2로 설정 -->
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
<!-- Custom JS -->
<script src="../js/script.js"></script>
</body>
</html>
