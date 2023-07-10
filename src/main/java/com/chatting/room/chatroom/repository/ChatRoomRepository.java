package com.chatting.room.chatroom.repository;

import com.chatting.room.chatroom.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
