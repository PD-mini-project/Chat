function login(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // 여기서 서버로 실제 로그인 정보를 보내고 처리하는 로직을 추가해야 합니다.
    // 이 예시에서는 단순히 로그인 성공 여부를 콘솔에 출력하는 것으로 가정합니다.
    console.log(`Login Attempt: Username - ${username}, Password - ${password}`);
}

function signup(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // 여기서 서버로 회원가입 정보를 보내고 처리하는 로직을 추가해야 합니다.
    // 이 예시에서는 단순히 회원가입 성공 여부를 콘솔에 출력하는 것으로 가정합니다.
    console.log(`Sign Up Attempt: Username - ${username}, Password - ${password}`);
}