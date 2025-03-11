document.addEventListener('DOMContentLoaded', function() {
    // 파티클 배경 초기화
    initParticles();

    // 음악 버튼 이벤트
    setupMusicToggle();

    // 캐릭터 이벤트 리스너 설정
    setupCharacterEvents();

    // 시작 버튼 이벤트
    document.getElementById('startButton').addEventListener('mouseenter', function() {
        this.classList.add('pulse');
    });

    document.getElementById('startButton').addEventListener('mouseleave', function() {
        this.classList.remove('pulse');
    });

});

// 파티클 배경 초기화
function initParticles() {
    particlesJS("particles-js", {
        particles: {
            number: {
                value: 80,
                density: {
                    enable: true,
                    value_area: 800
                }
            },
            color: {
                value: "#FF69B4"
            },
            shape: {
                type: "circle"
            },
            opacity: {
                value: 0.5,
                random: true
            },
            size: {
                value: 3,
                random: true
            },
            line_linked: {
                enable: true,
                distance: 150,
                color: "#FFD700",
                opacity: 0.4,
                width: 1
            },
            move: {
                enable: true,
                speed: 2,
                direction: "none",
                random: true,
                straight: false,
                out_mode: "out",
                bounce: false
            }
        },
        interactivity: {
            detect_on: "canvas",
            events: {
                onhover: {
                    enable: true,
                    mode: "repulse"
                },
                onclick: {
                    enable: true,
                    mode: "push"
                },
                resize: true
            }
        },
        retina_detect: true
    });
}

// 음악 버튼 설정 - 오디오 태그 사용 방식으로 변경
function setupMusicToggle() {
    const bgMusic = document.getElementById('bgMusic');
    bgMusic.volume = 0.5;

    // 디버깅을 위한 이벤트 리스너
    bgMusic.addEventListener('canplaythrough', () => {
        console.log('🎵 오디오 로드 완료, 재생 가능');
    });

    bgMusic.addEventListener('error', (e) => {
        console.error('🔴 오디오 로드 실패:', e);
    });

    // 사용자 상호작용 시 음악 재생 시작 (브라우저 정책 우회)
    document.addEventListener('click', function() {
        bgMusic.play().catch(error => console.log("🔴 음악 재생 실패:", error));
    }, { once: true });

    // 음악 토글 버튼 설정
    const musicToggle = document.getElementById('musicToggle');
    const musicIcon = document.getElementById('musicIcon');

    musicToggle.addEventListener('click', function() {
        if (bgMusic.paused) {
            bgMusic.play();
            musicIcon.className = 'fas fa-volume-up';
        } else {
            bgMusic.pause();
            musicIcon.className = 'fas fa-volume-mute';
        }
    });
}


// 캐릭터 이벤트 설정
function setupCharacterEvents() {
    const characters = document.querySelectorAll('.character');
    const dialogBox = document.getElementById('dialogBox');
    const dialogCharacterName = document.getElementById('dialogCharacterName');
    const dialogText = document.getElementById('dialogText');
    const dialogChoices = document.getElementById('dialogChoices');
    const choiceYes = document.getElementById('choiceYes');
    const choiceNo = document.getElementById('choiceNo');

    // 캐릭터 정보
    const characterData = {
        'character1': {
            name: '하나낭',
            dialog: '학생회 일이 바빠서 말이야. 혹시... 도와줄 생각 있어? 별로 너가 필요해서 그런 건 아니니까 오해하지 마!',
            choices: ['물론이지, 도와줄게!', '미안해, 지금은 다른 일이 있어.']
        },
        'character2': {
            name: '배나낭',
            dialog: '여기서 만나다니 나 완전 럭키 비키쟈나~❤️ 혹시 이번 주말에 시간 있어? 같이 영화 보러 갈래?',
            choices: ['좋아, 어떤 영화 보고싶어?', '미안해, 이번 주말은 약속이 있어.']
        }
    };

    // 각 캐릭터에 클릭 이벤트 추가
    characters.forEach(character => {
        character.addEventListener('click', function() {
            const id = this.id;
            const data = characterData[id];

            // 말풍선 표시
            const speechBubble = this.querySelector('.character-speech-bubble');
            speechBubble.classList.remove('d-none');

            // 다이얼로그 박스 업데이트 및 표시
            dialogCharacterName.textContent = data.name;
            dialogText.innerHTML = '';
            dialogBox.classList.add('active');

            // 타이핑 효과로 텍스트 표시
            typeText(data.dialog, dialogText);

            // 선택지 업데이트
            choiceYes.textContent = data.choices[0];
            choiceNo.textContent = data.choices[1];

            // 선택지 이벤트
            setupChoiceEvents(id);
        });

        // 말풍선 표시 이벤트
        character.addEventListener('mouseenter', function() {
            const speechBubble = this.querySelector('.character-speech-bubble');
            speechBubble.classList.remove('d-none');
        });

        character.addEventListener('mouseleave', function() {
            const speechBubble = this.querySelector('.character-speech-bubble');
            speechBubble.classList.add('d-none');
        });
    });

    // 다이얼로그 박스 닫기 (next 버튼)
    document.querySelector('.dialog-next').addEventListener('click', function() {
        dialogBox.classList.remove('active');
    });
}

// 선택지 이벤트 설정
function setupChoiceEvents(characterId) {
    const choiceYes = document.getElementById('choiceYes');
    const choiceNo = document.getElementById('choiceNo');
    const dialogBox = document.getElementById('dialogBox');
    const dialogText = document.getElementById('dialogText');

    // 긍정적 선택지
    choiceYes.onclick = function() {
        let response = "";

        switch(characterId) {
            case 'character1':
                response = "고마워... 뭐, 큰 도움이 될지는 모르겠지만! 그럼 내일 방과 후에 학생회실로 와줘. 기다릴게...";
                break;
            case 'character2':
                response = "좋아! 나는 공포 영화 보고싶어!! 너는 너는? 우리 이번주 토요일 7시, CGV에서 만나기다~❤️";
                break;
        }

        // 응답 표시
        dialogText.innerHTML = '';
        typeText(response, dialogText);

        // 선택지 숨기기
        document.getElementById('dialogChoices').style.display = 'none';

        // 4초 후 대화창 닫기
        setTimeout(function() {
            dialogBox.classList.remove('active');
            // 1초 후 선택지 다시 표시 (다음 대화를 위해)
            setTimeout(function() {
                document.getElementById('dialogChoices').style.display = 'flex';
            }, 1000);
        }, 40000);
    };

    // 부정적 선택지
    choiceNo.onclick = function() {
        let response = "";

        switch(characterId) {
            case 'character1':
                response = "...어차피 상관 없어. 별로 기대한 건 아니니까...";
                break;
            case 'character2':
                response = "에구, 그랬구나... 알겠어! 다음에 시간 날 때 같이 놀아줘❤️";
                break;
        }

        // 응답 표시
        dialogText.innerHTML = '';
        typeText(response, dialogText);

        // 선택지 숨기기
        document.getElementById('dialogChoices').style.display = 'none';

        // 4초 후 대화창 닫기
        setTimeout(function() {
            dialogBox.classList.remove('active');
            // 1초 후 선택지 다시 표시 (다음 대화를 위해)
            setTimeout(function() {
                document.getElementById('dialogChoices').style.display = 'flex';
            }, 1000);
        }, 4000);
    };
}

// 타이핑 효과 함수
function typeText(text, element, speed = 50) {
    let i = 0;
    element.innerHTML = '';
    element.classList.add('typing');

    function typing() {
        if (i < text.length) {
            element.innerHTML += text.charAt(i);
            i++;
            setTimeout(typing, speed);
        } else {
            element.classList.remove('typing');
        }
    }

    typing();
}