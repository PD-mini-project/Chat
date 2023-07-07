package com.chatting.room.chatroom.repository;

import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.chatroom.dto.response.ChatRoomRespDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    void deleteById(Long id);
    ChatRoom save(ChatRoom chatRoom);


    List<ChatRoomRespDto> chatRoomList();

    List<ChatRoom> findAllWithCategories();

    Optional<ChatRoom> findByIdWithCategories(Long chatRoomId);
}
