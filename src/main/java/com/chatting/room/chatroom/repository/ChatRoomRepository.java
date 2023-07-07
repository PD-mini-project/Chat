package com.chatting.room.chatroom.repository;

import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.chatroom.dto.response.ChatRoomResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
