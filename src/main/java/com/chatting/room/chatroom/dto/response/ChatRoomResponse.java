package com.chatting.room.chatroom.dto.response;

import com.chatting.room.chatroom.domain.Category;
import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ChatRoomResponse {

    private final Long id;

    private final String title;

    private final String description;

    private final Long userId;

    private final String username;

    private final List<CategoryResponse> categoryResponseList;

    public ChatRoomResponse(Long id, String title, String description, Long userId, String username, List<CategoryResponse> categoryResponseList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.username = username;
        this.categoryResponseList = categoryResponseList;
    }

    private static List<CategoryResponse> mapToResponse(List<Category> categoryList) {
        return categoryList.stream()
                .map(Category::getTitle)
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public static ChatRoomResponse from(User user, ChatRoom chatRoom, List<Category> categoryList) {
        return ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .title(chatRoom.getTitle())
                .description(chatRoom.getDescription())
                .userId(user.getId())
                .username(user.getUsername())
                .categoryResponseList(mapToResponse(categoryList))
                .build();
    }
}