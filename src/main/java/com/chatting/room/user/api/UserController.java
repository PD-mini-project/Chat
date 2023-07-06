package com.chatting.room.user.api;

import com.chatting.room.user.application.UserService;
import com.chatting.room.user.dto.request.UserLoginRequest;
import com.chatting.room.user.dto.request.UserCreateRequest;
import com.chatting.room.user.dto.response.UserResponse;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserCreateRequest userReq) {
        userService.registerUser(userReq);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        boolean loggedIn = userService.loginUser(userLoginRequest);

        if (loggedIn) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo() {
        UserResponse userResponse = userService.getUserInfo();
        if (userResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUserInfo(@RequestBody @Valid UserCreateRequest userReq) {
        userService.updateUserInfo(userReq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUserList() {
        List<UserResponse> userList = userService.getUserList();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
}