package com.chatting.room.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResetPasswordDto {

    private String username;
    private String newPassword;
}