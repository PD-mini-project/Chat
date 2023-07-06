package com.chatting.room.chatroom.application;

import com.chatting.room.chatroom.domain.Categories;
import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.chatroom.domain.Chatroom_Categories;

import com.chatting.room.chatroom.repository.CategoriesRepository;
import com.chatting.room.chatroom.repository.ChatRoomRepository;
import com.chatting.room.chatroom.repository.ChatroomCategoriesRepository;
import org.springframework.stereotype.Service;


@Service
public class ChatroomCategoriesService {
    private final ChatroomCategoriesRepository chatroomCategoriesRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final CategoriesRepository categoriesRepository;

    public ChatroomCategoriesService(ChatroomCategoriesRepository chatroomCategoriesRepository, ChatRoomRepository chatRoomRepository, CategoriesRepository categoriesRepository) {
        this.chatroomCategoriesRepository = chatroomCategoriesRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public Chatroom_Categories getChatroomCategories(Long chatroomId) {
        // chatroomId를 이용하여 Chatroom_Categories를 조회하는 로직 구현
        return chatroomCategoriesRepository.findById(chatroomId)
                .orElseThrow(() -> new RuntimeException("Chatroom_Categories not found"));
    }

    public Chatroom_Categories createChatroomCategories(Chatroom_Categories chatroomCategories) {
        // Chatroom_Categories를 생성하는 로직 구현
        return chatroomCategoriesRepository.save(chatroomCategories);
    }

    public void deleteChatroomCategories(Long chatroomId, Long categoryId) {
        // chatroomId와 categoryId를 이용하여 Chatroom_Categories를 삭제하는 로직 구현
        chatroomCategoriesRepository.deleteById(new Chatroom_Categories.ChatroomCategoriesId(chatroomId, categoryId));
    }

    public void linkChatRoomWithCategory(Long chatRoomId, Long categoryId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));

        Categories category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Chatroom_Categories chatroomCategories = new Chatroom_Categories(chatRoom.getId(), category.getId());
        chatroomCategoriesRepository.save(chatroomCategories);
    }

    public void unlinkChatRoomWithCategory(Long chatRoomId, Long categoryId) {
        Chatroom_Categories.ChatroomCategoriesId id = new Chatroom_Categories.ChatroomCategoriesId(chatRoomId, categoryId);
        chatroomCategoriesRepository.deleteById(id);
    }
}