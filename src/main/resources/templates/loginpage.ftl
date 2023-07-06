<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <style>
        /* CSS styles here */
        body {
            background: #2c2c2c;
            font-family: "Raleway";
        }
        .form {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            height: 250px;
            padding: 10px;
        }
        .form .form-header {
            height: 30px;
            display: flex;
            align-items: center;
            text-align: center;
        }
        .form .form-header > div {
            width: calc(100% / 3);
            color: #ddd;
            font-size: 18px;
            font-weight: 600;
            cursor: pointer;
        }
        .form.signup .form-header div.show-signup {
            color: #f1575b;
        }
        .form.signin .form-header div.show-signin {
            color: #f1575b;
        }
        .form.reset .form-header div.show-reset {
            color: #f1575b;
        }
        .form .form-elements {
            margin-top: 15px;
        }
        .form .form-elements .form-element {
            height: 50px;
            opacity: 1;
            overflow: hidden;
            transition: all 500ms ease-in-out;
        }
        .form .form-elements input {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            margin: 5px 0px;
            border-radius: 10px;
            box-sizing: border-box;
            border: 1px solid #222;
            background: #f5f5f5;
        }
        .form .form-elements button {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            font-weight: 600;
            margin-top: 5px;
            border-radius: 10px;
            background: #f1575b;
            color: #f5f5f5;
            cursor: pointer;
            border: none;
            outline: none;
        }
        .form .arrow {
            position: absolute;
            top: 51px;
            width: 0;
            height: 0;
            border-left: 10px solid transparent;
            border-right: 10px solid transparent;
            border-bottom: 10px solid #f5f5f5;
        }
        .form.signup .arrow {
            left: calc(calc(100% / 3) / 2);
        }
        .form.signin .arrow {
            left: calc(calc(100% / 3) + calc(calc(100% / 3) / 2.2));
        }
        .form.reset .arrow {
            left: calc(calc(calc(100% / 3) * 2) + calc(calc(100% / 3) / 3));
        }
        .form.signin .form-elements > div:nth-child(3) {
            height: 0px;
            opacity: 0;
        }


    </style>
</head>
<body>
<h1>Login Page</h1>
<div class="form">
    <div class="form-header">
        <div class="show-signup">SignUp</div>
        <div class="show-signin">Sign In</div>
        <div class="show-reset">Reset</div>
    </div>
    <div class="arrow"></div>
    <div class="form-elements">
        <div class="form-element">
            <input type="text" placeholder="Username" id="usernameInput">
        </div>
        <div class="form-element">
            <input type="password" placeholder="Password" id="passwordInput">
        </div>
        <div class="form-element">
            <input type="password" placeholder="Confirm password" id="confirmPasswordInput">
        </div>
        <div class="form-element">
            <button id="submit-btn">Sign Up</button>
            <button id="login-btn">Sign In</button>
            <button id="reset-btn">Reset password</button>
        </div>
    </div>
</div>

<script>
    function resetClass(element, classname) {
        element.classList.remove(classname);
    }

    document.getElementsByClassName("show-signup")[0].addEventListener("click", function () {
        let form = document.getElementsByClassName("form")[0];
        resetClass(form, "signin");
        resetClass(form, "reset");
        form.classList.add("signup");
        document.getElementById("submit-btn").innerText = "Sign Up";
    });

    document.getElementsByClassName("show-signin")[0].addEventListener("click", function () {
        let form = document.getElementsByClassName("form")[0];
        resetClass(form, "signup");
        resetClass(form, "reset");
        form.classList.add("signin");
        document.getElementById("submit-btn").innerText = "Sign In";
        document.getElementById("submit-btn").id = "login-btn";

    });

    document.getElementsByClassName("show-reset")[0].addEventListener("click", function () {
        let form = document.getElementsByClassName("form")[0];
        resetClass(form, "signup");
        resetClass(form, "signin");
        form.classList.add("reset");
        document.getElementById("submit-btn").innerText = "Reset password";
        document.getElementById("submit-btn").id = "reset-btn";

    });

    document.getElementById("submit-btn").addEventListener("click", function () {
        var username = document.getElementById("usernameInput").value;
        var password = document.getElementById("passwordInput").value;
        var confirmPassword = document.getElementById("confirmPasswordInput").value;

        if (!username || !password || !confirmPassword) {
            alert("Please enter username, password, and confirm password.");
            return;
        }

        if (password !== confirmPassword) {
            alert("Password and confirm password do not match.");
            return;
        }

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/api/user/register", true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 201) {
                    // 회원가입 성공
                    alert("Registration successful!");
                    window.location.href = "/main";  // main.ftl로 이동
                } else {
                    // 회원가입 실패
                    alert("Registration failed!");
                }
            }
        };
        var data = JSON.stringify({ username: username, password: password });
        xhr.send(data);
    });

    document.getElementById("login-btn").addEventListener("click", function () {
        var username = document.getElementById("usernameInput").value;
        var password = document.getElementById("passwordInput").value;

        if (!username || !password) {
            alert("Please enter username and password.");
            return;
        }

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/login", true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // 로그인 성공
                    alert("Login successful!");
                    window.location.href = "/main";  // main.ftl로 이동
                } else {
                    // 로그인 실패
                    alert("Login failed!");
                }
            }
        };
        var data = JSON.stringify({username: username, password: password});
        xhr.send(data);
    });

    document.getElementById("reset-btn").addEventListener("click", function () {
        var username = document.getElementById("usernameInput").value;
        var newPassword = document.getElementById("passwordInput").value;
        var confirmPassword = document.getElementById("confirmPasswordInput").value;

        if (!username || !newPassword || !confirmPassword) {
            alert("Please enter username, new password, and confirm password.");
            return;
        }

        if (newPassword !== confirmPassword) {
            alert("New password and confirm password do not match.");
            return;
        }

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/api/user/resetpassword", true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // 비밀번호 재설정 성공
                    alert("Password reset successful!");
                } else {
                    // 비밀번호 재설정 실패
                    alert("Password reset failed!");
                }
            }
        };
        var data = JSON.stringify({ username: username, newPassword: newPassword });
        xhr.send(data);
    });
</script>
</body>
</html>