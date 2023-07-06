package com.chatting.room.chatroom.dto.response;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
public class ChatRoomRespDto {
    private final Long id;
    @Length(max = 255)
    private final String title;
    @Length(max = 255)
    private final String description;

    private Long userId;
    private String username;

    public ChatRoomRespDto(Long id, String title, String description, Long userId, String username, List<CategoryRespDto> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.username = username;
        this.categories = categories;
    }

    private final List<CategoryRespDto> categories;

}