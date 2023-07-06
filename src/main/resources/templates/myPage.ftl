<!DOCTYPE html>
<html>
<head>
    <title>My Page</title>
</head>
<body>
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
<div>
    <form id="withdrawForm">
        <button type="button" onclick="withdrawUser()">Withdraw</button>
    </form>
</div>

<script>
    function updateUserInfo() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var description = document.getElementById("description").value;

        var xhr = new XMLHttpRequest();
        xhr.open("PUT", "/api/user/update", true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    alert("User information updated successfully!");
                } else {
                    alert("Failed to update user information.");
                }
            }
        };
        var data = JSON.stringify({username: username, password: password, description: description});
        xhr.send(data);
    }

    function withdrawUser() {
        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", "/api/user/withdraw", true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 204) {
                    alert("User account successfully withdrawn!");
                    window.location.href = "/login";  // 로그인 페이지로 이동
                } else {
                    alert("Failed to withdraw user account.");
                }
            }
        };
        xhr.send();
    }
</script>
</body>
</html>
