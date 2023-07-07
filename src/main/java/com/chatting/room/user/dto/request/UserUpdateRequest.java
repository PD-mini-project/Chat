package com.chatting.room.user.dto.request;

import com.chatting.room.common.util.Patterns;
import com.chatting.room.user.domain.User;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    private Long id;

    @Pattern(regexp = Patterns.USERNAME)
    private String newUsername;

    @Pattern(regexp = Patterns.PASSWORD)
    private String newPassword;

    private String newDescription;

    public UserUpdateRequest(Long id, String newUsername, String newPassword, String newDescription) {
        this.id = id;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.newDescription = newDescription;
    }

    public static User toEntity(UserUpdateRequest request) {
        return User.builder()
                .username(request.getNewUsername())
                .password(request.getNewPassword())
                .description(request.getNewDescription())
                .build();
    }
}