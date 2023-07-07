package com.chatting.room.user.dto.response;

import com.chatting.room.user.domain.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String username;
    private final String description;

    public UserResponse(Long id, String username, String description) {
        this.id = id;
        this.username = username;
        this.description = description;
    }

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getDescription()
        );
    }
}
