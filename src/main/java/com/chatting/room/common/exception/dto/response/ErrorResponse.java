package com.chatting.room.common.exception.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    private final String errorCode;
    private final String message;
}
