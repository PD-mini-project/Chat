package com.chatting.room.chatroom.api;

import com.chatting.room.chatroom.application.ChatroomCategoriesService;
import com.chatting.room.chatroom.repository.CategoryRepository;
import com.chatting.room.chatroom.repository.ChatRoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/chatroom/categories")
public class ChatroomCategoriesController {
    private final ChatroomCategoriesService chatroomCategoriesService;
    private final ChatRoomRepository chatRoomRepository;
    private final CategoryRepository categoryRepository;

    public ChatroomCategoriesController(ChatroomCategoriesService chatroomCategoriesService, ChatRoomRepository chatRoomRepository, CategoryRepository categoryRepository) {
        this.chatroomCategoriesService = chatroomCategoriesService;
        this.chatRoomRepository = chatRoomRepository;
        this.categoryRepository = categoryRepository;
    }

    // 채팅방과 카테고리 연결
    @PostMapping("/{chatRoomId}/categories/{categoryId}")
    public ResponseEntity<Void> linkChatRoomWithCategory(
            @PathVariable("chatRoomId") Long chatRoomId,
            @PathVariable("categoryId") Long categoryId) {
        chatroomCategoriesService.linkChatRoomWithCategory(chatRoomId, categoryId);
        return ResponseEntity.ok().build();
    }

    // 채팅방과 카테고리 연결 해제
    @DeleteMapping("/{chatRoomId}/categories/{categoryId}")
    public ResponseEntity<Void> unlinkChatRoomWithCategory(
            @PathVariable("chatRoomId") Long chatRoomId,
            @PathVariable("categoryId") Long categoryId) {
        chatroomCategoriesService.unlinkChatRoomWithCategory(chatRoomId, categoryId);
        return ResponseEntity.ok().build();
    }

    // 채팅방 목록과 카테고리 조회 (조인)


    // 채팅방 상세 조회와 카테고리 조회 (조인)



}
