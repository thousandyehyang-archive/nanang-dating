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

window.addEventListener("pageshow", function(event) {
    if (event.persisted) { // 뒤로 가기로 돌아왔을 때
        console.log("🔄 뒤로 가기로 돌아왔습니다.");
        restoreAudioState(); // 음악 상태 복원
    }
});

function restoreAudioState() {
    const storedMusicState = localStorage.getItem('bgmPlaying') === 'true';
    const storedTime = parseFloat(localStorage.getItem('bgmTime')) || 0;

    if (bgMusic) {
        bgMusic.currentTime = storedTime;
        if (storedMusicState) {
            bgMusic.play().catch(e => console.log("Audio play failed:", e));
        }
    }
}

// 오디오 시스템 초기화
let bgMusic, isMusicPlaying = false;

function initAudio() {
    // LocalStorage에서 음악 상태 확인
    const storedMusicState = localStorage.getItem('bgmPlaying');
    const storedTime = parseFloat(localStorage.getItem('bgmTime')) || 0;

    // 배경 음악 설정
    bgMusic = new Audio(getContextPath() + '/audio/background.mp3');
    bgMusic.loop = true;
    bgMusic.volume = 0.5;
    bgMusic.currentTime = storedTime;

    if (storedMusicState === 'true') {
        bgMusic.play().catch(e => console.log("Audio play failed:", e));
        isMusicPlaying = true;
    }

    // 음악 토글 버튼 이벤트 설정
    const musicToggle = document.getElementById('music-toggle');

    musicToggle.addEventListener('click', function() {
        if (isMusicPlaying) {
            bgMusic.pause();
            musicToggle.innerHTML = '<i class="fas fa-volume-mute"></i>';
        } else {
            bgMusic.play().catch(e => console.log("Audio play failed:", e));
            musicToggle.innerHTML = '<i class="fas fa-volume-up"></i>';
        }
        isMusicPlaying = !isMusicPlaying;

        // 음악 상태 저장
        localStorage.setItem('bgmPlaying', isMusicPlaying.toString());
    });

    // 현재 음악 재생 시간을 저장 (1초마다)
    setInterval(() => {
        if (!bgMusic.paused) {
            localStorage.setItem('bgmTime', bgMusic.currentTime);
        }
    }, 1000);
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