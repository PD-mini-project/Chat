package com.chatting.room.chatroom.application;


import com.chatting.room.chatroom.domain.ChatRoom;

import com.chatting.room.chatroom.dto.request.CreateChatRoomRequest;
import com.chatting.room.chatroom.dto.request.UpdateChatRoomRequest;
import com.chatting.room.chatroom.dto.response.CategoryRespDto;
import com.chatting.room.chatroom.dto.response.ChatRoomRespDto;

import com.chatting.room.chatroom.repository.ChatRoomRepository;
import com.chatting.room.user.domain.User;
import com.chatting.room.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    public List<ChatRoomRespDto> getChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return convertToDtoList(chatRooms);
    }

    public List<ChatRoomRespDto> createChatRooms(List<CreateChatRoomRequest> requests) {
        List<ChatRoom> chatRooms = new ArrayList<>();

        for (CreateChatRoomRequest request : requests) {
            User user = userRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            ChatRoom chatRoom = new ChatRoom(request.getId(), request.getTitle(), request.getDescription(), user);
            chatRooms.add(chatRoom);
        }

        List<ChatRoom> savedChatRooms = chatRoomRepository.saveAll(chatRooms);
        return convertToDtoList(savedChatRooms);
    }

    public void deleteChatRoom(Long chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
    }

    public ChatRoomRespDto updateChatRoom(Long chatRoomId, UpdateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));

        // 수정할 필드 업데이트
        chatRoom.setTitle(request.getTitle());
        chatRoom.setDescription(request.getDescription());

        // 채팅방 업데이트
        ChatRoom updatedChatRoom = chatRoomRepository.save(chatRoom);

        return new ChatRoomRespDto(
                updatedChatRoom.getId(),
                updatedChatRoom.getTitle(),
                updatedChatRoom.getDescription(),
                updatedChatRoom.getUser().getId(),
                updatedChatRoom.getUser().getUsername(),
                (List<CategoryRespDto>) updatedChatRoom.getUser()
        );
    }

    private List<ChatRoomRespDto> convertToDtoList(List<ChatRoom> chatRooms) {
        return chatRooms.stream()
                .map(chatRoom -> new ChatRoomRespDto(
                        chatRoom.getId(),
                        chatRoom.getTitle(),
                        chatRoom.getDescription(),
                        chatRoom.getUser().getId(),
                        chatRoom.getUser().getUsername(),
                        (List<CategoryRespDto>) chatRoom.getUser()

                ))
                .collect(Collectors.toList());
    }
}
