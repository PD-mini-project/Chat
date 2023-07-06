package com.chatting.room.chatroom.repository;

import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.chatroom.dto.response.ChatRoomResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query(value = "select " +
            "new com.chatting.room.chatroom.dto.response.ChatRoomResp(c.id, c.title, c.description, u.id, u.username) " +
            "from ChatRoom c " +
            "left join User u " +
            "on c.user.id = u.id")
    List<ChatRoomResp> chatRoomList();
}
