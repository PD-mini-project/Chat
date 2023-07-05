package com.chatting.room.chatroom.dto.response;


import lombok.Getter;

@Getter
public class ChatRoomResp {
    private final Long chatRoomId;

    private final String title;

    private final String description;

    private final Long userId;

    private final String username;

    public ChatRoomResp(Long chatRoomId, String title, String description, Long userId, String username) {
        this.chatRoomId = chatRoomId;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.username = username;
    }
}
