<!DOCTYPE html>
<html>
<head>
    <title>My Page</title>
    <style>
        body {
            background: #2c2c2c;
            font-family: "Raleway";
            color: #f5f5f5;
        }

        h1 {
            text-align: center;
            font-size: 45px;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .box {
            background: rgba(34, 34, 34, 0.6);
            width: 300px;
            padding: 45px;
        }

        form {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            margin: 5px 0px;
            border-radius: 10px;
            box-sizing: border-box;
            border: 1px solid #222;
            background: #f5f5f5;
        }

        button {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            font-weight: 600;
            margin-top: 35px;
            border-radius: 10px;
            background: #f1575b;
            color: #f5f5f5;
            cursor: pointer;
            border: none;
            outline: none;
        }

        button.withdraw {
            background: #f1575b;
        }

        .message {
            text-align: center;
            margin-top: 50px;
        }

        updatebutton {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            font-weight: 600;
            margin-top: 30px;
            border-radius: 10px;
            background: #f1575b;
            color: #f5f5f5;
            cursor: pointer;
            border: none;
            outline: none;
        }


        withdrawbutton {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            font-weight: 600;
            border-radius: 10px;
            background: #f1575b;
            color: #f5f5f5;
            cursor: pointer;
            border: none;
            outline: none;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="box">
        <h1>My Page</h1>
        <form id="updateForm">
            <div>
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${user.username}">
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="${user.password}">
            </div>
            <div>
                <label for="description">Description:</label>
                <input type="text" id="description" name="description" value="${user.description}">
            </div>
            <div>
                <button type="button" onclick="updateUserInfo()">Update</button>
            </div>
        </form>
        <form id="withdrawForm">
            <button type="button" class="withdraw" onclick="withdrawUser()">Withdraw</button>
        </form>
        <div class="message" id="message"></div>
    </div>
</div>

<script>
    function updateUserInfo() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var description = document.getElementById("description").value;

        var xhr = new XMLHttpRequest();
        xhr.open("PUT", "/api/user/update", true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    showMessage("User information updated successfully!", true);
                } else {
                    showMessage("Failed to update user information.", false);
                }
            }
        };
        var data = JSON.stringify({username: username, password: password, description: description});
        xhr.send(data);
    }

    function withdrawUser() {
        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", "/api/user/withdraw", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 204) {
                    showMessage("User account successfully withdrawn!", true);
                    window.location.href = "/login"; // 로그인 페이지로 이동
                } else {
                    showMessage("Failed to withdraw user account.", false);
                }
            }
        };
        xhr.send();
    }

    function showMessage(message, success) {
        var messageDiv = document.getElementById("message");
        messageDiv.textContent = message;
        messageDiv.style.color = success ? "green" : "red";
    }
</script>
</body>
</html>