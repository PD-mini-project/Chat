package com.chatting.room.chatroom.application;

import com.chatting.room.chatroom.domain.Category;
import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.chatroom.domain.Chatroom_Category;

import com.chatting.room.chatroom.repository.CategoryRepository;
import com.chatting.room.chatroom.repository.ChatRoomRepository;
import com.chatting.room.chatroom.repository.ChatroomCategoriesRepository;
import org.springframework.stereotype.Service;


@Service
public class ChatroomCategoriesService {
    private final ChatroomCategoriesRepository chatroomCategoriesRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final CategoryRepository categoryRepository;

    public ChatroomCategoriesService(ChatroomCategoriesRepository chatroomCategoriesRepository, ChatRoomRepository chatRoomRepository, CategoryRepository categoryRepository) {
        this.chatroomCategoriesRepository = chatroomCategoriesRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.categoryRepository = categoryRepository;
    }

    public Chatroom_Category getChatroomCategories(Long chatroomId) {
        // chatroomId를 이용하여 Chatroom_Categories를 조회하는 로직 구현
        return chatroomCategoriesRepository.findById(chatroomId)
                .orElseThrow(() -> new RuntimeException("Chatroom_Categories not found"));
    }

    public Chatroom_Category createChatroomCategories(Chatroom_Category chatroomCategories) {
        // Chatroom_Categories를 생성하는 로직 구현
        return chatroomCategoriesRepository.save(chatroomCategories);
    }

    public void deleteChatroomCategories(Long chatroomId, Long categoryId) {
        // chatroomId와 categoryId를 이용하여 Chatroom_Categories를 삭제하는 로직 구현
        chatroomCategoriesRepository.deleteById(new Chatroom_Category.ChatroomCategoriesId(chatroomId, categoryId));
    }

    public void linkChatRoomWithCategory(Long chatRoomId, Long categoryId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Chatroom_Category chatroomCategories = new Chatroom_Category(chatRoom.getId(), category.getId());
        chatroomCategoriesRepository.save(chatroomCategories);
    }

    public void unlinkChatRoomWithCategory(Long chatRoomId, Long categoryId) {
        Chatroom_Category.ChatroomCategoriesId id = new Chatroom_Category.ChatroomCategoriesId(chatRoomId, categoryId);
        chatroomCategoriesRepository.deleteById(id);
    }
}