package com.chatting.room.user.dto.request;

import com.chatting.room.common.util.Patterns;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserLoginRequest {

    @Pattern(regexp = Patterns.USERNAME)
    private String username;

    @Pattern(regexp = Patterns.PASSWORD)
    private String password;

    public UserLoginRequest(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
