package com.chatting.room.chatroom.dto.response;

import lombok.Getter;

@Getter
public class CategoryRespDto {
    private final Long categoryId;
    private final String title;

    public CategoryRespDto(Long categoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
    }
}