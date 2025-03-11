// 파티클 효과 초기화
document.addEventListener('DOMContentLoaded', function() {
    // 파티클 시스템 초기화
    initParticles();

    activateDialogBox();

    // 오디오 시스템 초기화
    initAudio();

    // 캐릭터 카드 이벤트 초기화
    initCharacterCards();
});

function activateDialogBox() {
    const dialogBox = document.querySelector(".dialog-box");
    if (dialogBox) {
        dialogBox.classList.add("active");
        console.log("Dialog box activated");
    }
}

// 파티클 시스템 초기화
function initParticles() {
    particlesJS("particles-js", {
        "particles": {
            "number": {
                "value": 80,
                "density": {
                    "enable": true,
                    "value_area": 800
                }
            },
            "color": {
                "value": "#ff69b4"
            },
            "shape": {
                "type": "circle",
                "stroke": {
                    "width": 0,
                    "color": "#000000"
                }
            },
            "opacity": {
                "value": 0.5,
                "random": true
            },
            "size": {
                "value": 3,
                "random": true
            },
            "line_linked": {
                "enable": false
            },
            "move": {
                "enable": true,
                "speed": 2,
                "direction": "top",
                "random": true,
                "straight": false,
                "out_mode": "out",
                "bounce": false
            }
        },
        "interactivity": {
            "detect_on": "canvas",
            "events": {
                "onhover": {
                    "enable": true,
                    "mode": "bubble"
                },
                "onclick": {
                    "enable": true,
                    "mode": "repulse"
                },
                "resize": true
            },
            "modes": {
                "bubble": {
                    "distance": 150,
                    "size": 6,
                    "duration": 2,
                    "opacity": 0.8,
                    "speed": 3
                },
                "repulse": {
                    "distance": 200,
                    "duration": 0.4
                }
            }
        },
        "retina_detect": true
    });
}

// 오디오 관련 변수들
let bgMusic, hoverSound, clickSound;
let isMusicPlaying = false;

// 오디오 시스템 초기화
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

// 캐릭터 카드 이벤트 초기화
function initCharacterCards() {
    const characterCards = document.querySelectorAll('.character-card');
    const selectBtns = document.querySelectorAll('.select-btn');

    // 캐릭터 카드 호버 이벤트
    characterCards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            if (isMusicPlaying) {
                hoverSound.currentTime = 0;
                hoverSound.play().catch(e => console.log("Hover sound failed:", e));
            }
        });
    });

    // 선택 버튼 클릭 이벤트
    selectBtns.forEach(btn => {
        btn.addEventListener('click', function(e) {
            if (isMusicPlaying) {
                clickSound.play().catch(e => console.log("Click sound failed:", e));
            }
            // 클릭 시 지연 후 이동
            e.preventDefault();
            const href = this.getAttribute('href');

            this.classList.add('active');

            setTimeout(() => {
                window.location.href = href;
            }, 500);
        });
    });
}

// 컨텍스트 경로 가져오기 함수
function getContextPath() {
    const baseUrl = window.location.pathname;
    return baseUrl.substring(0, baseUrl.indexOf('/', 1));
}