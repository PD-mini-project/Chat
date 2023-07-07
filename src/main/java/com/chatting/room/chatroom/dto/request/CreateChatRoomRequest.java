package com.chatting.room.chatroom.dto.request;

import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.user.domain.User;

import lombok.Getter;


@Getter
public class CreateChatRoomRequest {

    private final String title;

    private final String description;

    private final String categories;

    public CreateChatRoomRequest(String title, String description, String categories) {
        this.title = title;
        this.description = description;
        this.categories = categories;
    }

    public static ChatRoom toEntity(CreateChatRoomRequest request, User user) {
        return ChatRoom.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .user(user)
                .build();
    }
}
