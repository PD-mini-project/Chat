package com.chatting.room.user.dto.request;

import com.chatting.room.common.util.Patterns;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserCreateRequest {

    @Pattern(regexp = Patterns.USERNAME)
    private String username;

    @Pattern(regexp = Patterns.PASSWORD)
    private String password;

    private String description;

    public UserCreateRequest(final String username, final String password, String description) {
        this.username = username;
        this.password = password;
        this.description = description;
    }
}
