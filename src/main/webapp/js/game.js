document.addEventListener('DOMContentLoaded', function() {
    // 대화창 타이핑 효과
    const characterDialog = document.getElementById('characterDialog');
    if (characterDialog && characterDialog.textContent.trim() !== '') {
        const originalText = characterDialog.textContent;
        characterDialog.textContent = '';

        let i = 0;
        function typeWriter() {
            if (i < originalText.length) {
                characterDialog.textContent += originalText.charAt(i);
                i++;
                setTimeout(typeWriter, 30);
            }
        }
        typeWriter();
    }

    // 폼 제출 이벤트
    const gameForm = document.getElementById("gameForm");
    const userInput = document.getElementById("userInput");

    if (gameForm && userInput) {
        userInput.addEventListener("keydown", function (event) {
            if (event.key === "Enter" && !event.shiftKey) {
                event.preventDefault(); // 기본 Enter 줄바꿈 방지

                // 입력값이 비어있지 않은 경우에만 폼 제출
                if (userInput.value.trim() !== "") {
                    gameForm.submit(); // 폼을 명확히 제출
                }
            }
        });

        // 폼 제출 시 버튼 비활성화 (연속 제출 방지)
        gameForm.addEventListener("submit", function (event) {
            const submitButton = gameForm.querySelector(".send-button");
            if (submitButton) {
                submitButton.disabled = true;
                submitButton.innerHTML = "대답을 기다리는 중...";
            }
        });
    }

    // 캐릭터 이미지 애니메이션
    const characterImage = document.querySelector('.character-image');
    if (characterImage) {
        characterImage.classList.add('animate__fadeIn');
        setInterval(() => {
            const animations = ['animate__pulse', 'animate__headShake', 'animate__tada'];
            animations.forEach(anim => {
                characterImage.classList.remove(anim);
            });
            const randomAnim = animations[Math.floor(Math.random() * animations.length)];
            characterImage.classList.add(randomAnim);
            setTimeout(() => {
                characterImage.classList.remove(randomAnim);
            }, 1000);
        }, 5000);
    }

    // 음악 토글 기능
    const musicToggle = document.getElementById('musicToggle');
    if (musicToggle) {
        const bgMusic = document.createElement('audio');
        bgMusic.id = 'bgMusic';
        bgMusic.loop = true;
        bgMusic.volume = 0.3;
        bgMusic.src = 'audio/background-music.mp3';
        document.body.appendChild(bgMusic);

        let isMusicPlaying = false;
        musicToggle.addEventListener('click', function() {
            if (isMusicPlaying) {
                bgMusic.pause();
                musicToggle.innerHTML = '<i class="fas fa-volume-mute"></i>';
            } else {
                bgMusic.play().catch(error => {
                    console.log('음악 재생 실패:', error);
                });
                musicToggle.innerHTML = '<i class="fas fa-volume-up"></i>';
            }
            isMusicPlaying = !isMusicPlaying;
        });
    }

    // 반응형 이벤트
    function adjustUI() {
        const windowHeight = window.innerHeight;
        if (windowHeight < 600) {
            if (characterImage) {
                characterImage.style.maxHeight = '50vh';
            }
        } else {
            if (characterImage) {
                characterImage.style.maxHeight = '';
            }
        }
    }

    adjustUI();
    window.addEventListener('resize', adjustUI);
});
