package com.chatting.room.user.dto.response;

import lombok.Getter;

@Getter
public class UserResponse {

    private final String username;

    private final String description;

    public UserResponse(String username, String description) {
        this.username = username;
        this.description = description;
    }
}
