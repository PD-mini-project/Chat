package com.chatting.room.chatroom.dto.request;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_room")
public class UpdateChatRoomRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    @Length(max = 255)
    private String title;

    @Column(name = "description")
    @Length(max = 255)
    private String description;

    public UpdateChatRoomRequest(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}