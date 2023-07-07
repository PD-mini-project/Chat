package com.chatting.room.user.api;

import com.chatting.room.user.application.UserService;
import com.chatting.room.user.dto.request.UserLoginRequest;
import com.chatting.room.user.dto.request.UserCreateRequest;
import com.chatting.room.user.dto.request.UserUpdateRequest;
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

    @PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody @Valid UserCreateRequest userReq) {
        Long savedUserId = userService.createUser(userReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserId);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody @Valid UserLoginRequest request) {
        UserResponse userResponse = userService.loginUser(request);

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    // 권한 인증 애노테이션 필요 @Authentication
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        Long userId = userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }

    // 권한 인증 애노테이션 필요 @Authentication
    @GetMapping("/info/{id}")
    public ResponseEntity<UserResponse> userInfo(@PathVariable Long id) {
        UserResponse userResponse = userService.userInfo(id);

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    // 권한 인증 애노테이션 필요 @Authentication
    @PutMapping("/update")
    public ResponseEntity<Void> updateUserInfo(@RequestBody @Valid UserUpdateRequest request) {
        userService.updateUserInfo(request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 권한 인증 애노테이션 필요 @Authentication
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUserList() {
        List<UserResponse> userList = userService.getUserList();

        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
}