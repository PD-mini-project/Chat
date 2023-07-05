package com.chatting.room.user.api;

import com.chatting.room.user.application.UserService;
import com.chatting.room.user.dto.response.UserRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 샘플
    @GetMapping("/users")
    public ResponseEntity<List<UserRespDto>> users() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.userList());
    }
}
