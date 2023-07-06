// WebSocket 서버 URL (실제 서버 주소로 변경해야 합니다)
const serverUrl = 'ws://your-websocket-server-url';

const chatMessages = document.getElementById('chatMessages');
const messageInput = document.getElementById('messageInput');
const sendButton = document.getElementById('sendButton');

// WebSocket 연결
const socket = new WebSocket(serverUrl);

// 연결이 열릴 때 이벤트 처리
socket.onopen = () => {
    showMessage('연결되었습니다.');
};

// 연결이 닫힐 때 이벤트 처리
socket.onclose = () => {
    showMessage('연결이 닫혔습니다.');
};

// 메시지 수신 이벤트 처리
socket.onmessage = (event) => {
    showMessage(event.data);
};

// 메시지 전송
function sendMessage() {
    const message = messageInput.value;
    if (message.trim() !== '') {
        socket.send(message);
        messageInput.value = '';
    }
}

// 메시지를 화면에 표시
function showMessage(message) {
    const messageElement = document.createElement('div');
    messageElement.textContent = message;
    chatMessages.appendChild(messageElement);
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

// 전송 버튼 클릭 이벤트 처리
sendButton.addEventListener('click', sendMessage);

// 입력창에서 엔터 키 입력 이벤트 처리
messageInput.addEventListener('keydown', (event) => {
    if (event.key === 'Enter') {
        sendMessage();
    }
});