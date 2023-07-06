package com.chatting.room.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApplicationException {

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
