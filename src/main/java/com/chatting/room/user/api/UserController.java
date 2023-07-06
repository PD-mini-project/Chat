package com.chatting.room.user.api;

import com.chatting.room.user.application.UserService;
import com.chatting.room.user.dto.request.UserLoginDto;
import com.chatting.room.user.dto.request.UserReq;
import com.chatting.room.user.dto.response.UserRespDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserReq userReq) {
        userService.registerUser(userReq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserLoginDto userLoginDto) {
        boolean loggedIn = userService.loginUser(userLoginDto);
        if (loggedIn) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/info")
    public ResponseEntity<UserRespDto> getUserInfo() {
        UserRespDto userRespDto = userService.getUserInfo();
        if (userRespDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userRespDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUserInfo(@RequestBody UserReq userReq) {
        userService.updateUserInfo(userReq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserRespDto>> getUserList() {
        List<UserRespDto> userList = userService.getUserList();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
}