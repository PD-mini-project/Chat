package com.chatting.room.chatroom.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chatroom_categories")
public class Chatroom_Categories {

    @EmbeddedId
    private ChatroomCategoriesId id;

    public Chatroom_Categories(Long chatroomId, Long categoryId) {
        this.id = new ChatroomCategoriesId(chatroomId, categoryId);
    }

    public Chatroom_Categories(ChatroomCategoriesId id) {
        this.id = id;
    }

    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class ChatroomCategoriesId implements Serializable {
        private Long chatroomId;
        private Long categoryId;
    }

}
