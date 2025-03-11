document.addEventListener('DOMContentLoaded', function () {
    const state = {
        bgMusic: null,
        isMusicPlaying: false,
    };

    initAudio();
    initEventListeners(state);
    adjustCharacterImageSize();
    window.addEventListener('resize', adjustCharacterImageSize);
});

/**
 * 이벤트 리스너 초기화
 */
function initEventListeners(state) {
    applyTypingEffect('characterDialog');

    initFormSubmission();

    initCharacterAnimation();
}

/**
 * 대화창 타이핑 효과 적용
 */
function applyTypingEffect(elementId) {
    const element = document.getElementById(elementId);
    if (!element || element.textContent.trim() === '') return;

    const originalText = element.textContent;
    element.textContent = '';
    let i = 0;

    function typeWriter() {
        if (i < originalText.length) {
            element.textContent += originalText.charAt(i);
            i++;
            setTimeout(typeWriter, 30);
        }
    }
    typeWriter();
}

/**
 * 게임 폼 제출 이벤트 핸들링
 */
function initFormSubmission() {
    const gameForm = document.getElementById("gameForm");
    const userInput = document.getElementById("userInput");

    if (!gameForm || !userInput) return;

    userInput.addEventListener("keydown", function (event) {
        if (event.key === "Enter" && !event.shiftKey) {
            event.preventDefault();
            if (userInput.value.trim() !== "") {
                gameForm.submit();
            }
        }
    });

    gameForm.addEventListener("submit", function () {
        const submitButton = gameForm.querySelector(".send-button");
        if (submitButton) {
            submitButton.disabled = true;
            submitButton.innerHTML = "대답을 기다리는 중...";
        }
    });
}

/**
 * 캐릭터 이미지 애니메이션 적용
 */
function initCharacterAnimation() {
    const characterImage = document.querySelector('.character-image');
    if (!characterImage) return;

    characterImage.classList.add('animate__fadeIn');

    setInterval(() => {
        const animations = ['animate__pulse', 'animate__headShake', 'animate__tada'];
        const randomAnim = animations[Math.floor(Math.random() * animations.length)];

        characterImage.classList.remove(...animations);
        characterImage.classList.add(randomAnim);

        setTimeout(() => characterImage.classList.remove(randomAnim), 1000);
    }, 5000);
}

// 오디오 관련 변수들
let bgMusic, hoverSound, clickSound;
let isMusicPlaying = false;

function initAudio() {
    // 배경 음악 설정
    bgMusic = new Audio(getContextPath() + '/audio/background.mp3');
    bgMusic.loop = true;
    bgMusic.volume = 0.5;


    // 음악 토글 버튼 이벤트 설정
    const musicToggle = document.getElementById('music-toggle');

    musicToggle.addEventListener('click', function() {
        if (isMusicPlaying) {
            bgMusic.pause();
            musicToggle.innerHTML = '<i class="fas fa-music"></i>';
        } else {
            bgMusic.play().catch(e => console.log("Audio play failed:", e));
            musicToggle.innerHTML = '<i class="fas fa-volume-up"></i>';
        }
        isMusicPlaying = !isMusicPlaying;
    });
}

/**
 * 반응형 캐릭터 이미지 크기 조정
 */
function adjustCharacterImageSize() {
    const characterImage = document.querySelector('.character-image');
    if (!characterImage) return;

    characterImage.style.maxHeight = window.innerHeight < 600 ? '50vh' : '';
}

// 컨텍스트 경로 가져오기 함수
function getContextPath() {
    const baseUrl = window.location.pathname;
    return baseUrl.substring(0, baseUrl.indexOf('/', 1));
}