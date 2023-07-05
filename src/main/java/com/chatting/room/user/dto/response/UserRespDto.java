package com.chatting.room.user.dto.response;

import lombok.Getter;

@Getter
public class UserRespDto {

    private final String username;

    private final String description;

    public UserRespDto(String username, String description) {
        this.username = username;
        this.description = description;
    }
}
