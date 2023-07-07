package com.chatting.room.chatroom.dto.response;

import lombok.Getter;

@Getter
public class CategoryResponse {

    private final String title;

    public CategoryResponse(String title) {
        this.title = title;
    }
}