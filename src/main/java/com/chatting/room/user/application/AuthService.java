package com.chatting.room.user.application;

import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.chatroom.execption.ChatRoomNotFoundException;
import com.chatting.room.chatroom.repository.ChatRoomRepository;
import com.chatting.room.user.domain.User;
import com.chatting.room.user.exception.UserNotFoundException;
import com.chatting.room.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    public AuthService(UserRepository userRepository, ChatRoomRepository chatRoomRepository) {
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public boolean isAuthor(Long chatRoomId, Long userId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return chatRoom.getUser().getId().equals(user.getId());
    }
}
