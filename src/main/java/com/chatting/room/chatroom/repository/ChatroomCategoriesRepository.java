package com.chatting.room.chatroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatting.room.chatroom.domain.Chatroom_Categories;

import java.util.Optional;

public interface ChatroomCategoriesRepository extends JpaRepository<Chatroom_Categories, Long> {
    void deleteById(Chatroom_Categories.ChatroomCategoriesId chatroomCategoriesId);


    Optional<Chatroom_Categories> findById(Chatroom_Categories.ChatroomCategoriesId chatroomCategoriesId);
}