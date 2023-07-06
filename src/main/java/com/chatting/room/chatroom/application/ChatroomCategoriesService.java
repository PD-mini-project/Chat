package com.chatting.room.chatroom.application;

import com.chatting.room.chatroom.domain.Chatroom_Categories;
import com.chatting.room.chatroom.repository.ChatroomCategoriesRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatroomCategoriesService {
    private final ChatroomCategoriesRepository chatroomCategoriesRepository;

    public ChatroomCategoriesService(ChatroomCategoriesRepository chatroomCategoriesRepository) {
        this.chatroomCategoriesRepository = chatroomCategoriesRepository;
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
    public void updateChatroomCategories(Long chatroomId, Long categoryId) {
        Chatroom_Categories chatroomCategories = chatroomCategoriesRepository.findById(new Chatroom_Categories.ChatroomCategoriesId(chatroomId, categoryId))
                .orElseThrow(() -> new RuntimeException("Chatroom_Categories not found"));
    }

}