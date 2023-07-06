package com.chatting.room.chatroom.domain;

import com.chatting.room.common.domain.BaseEntity;
import com.chatting.room.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_room")
public class ChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    @Length(max = 255)
    private String title;

    @Column(name = "description")
    @Length(max = 255)
    private String description;
    @OnDelete(action = OnDeleteAction.CASCADE)
    // 유저가 없어지면 채팅방이 업어지도록
    @ManyToOne(fetch = FetchType.LAZY)
    // 외래키 매핑
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ChatRoom(Long id, String title, String description, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
    }

}

