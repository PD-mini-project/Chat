package com.chatting.room.chatroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatting.room.chatroom.domain.Chatroom_Category;

public interface ChatroomCategoriesRepository extends JpaRepository<Chatroom_Category, Long> {
    void deleteById(Chatroom_Category.ChatroomCategoriesId chatroomCategoriesId);

}