package com.chatting.room.chatroom.api;

import com.chatting.room.chatroom.application.ChatroomCategoriesService;
import com.chatting.room.chatroom.domain.Chatroom_Categories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatroom/categories")
public class ChatroomCategoriesController {
    private final ChatroomCategoriesService chatroomCategoriesService;

    public ChatroomCategoriesController(ChatroomCategoriesService chatroomCategoriesService) {
        this.chatroomCategoriesService = chatroomCategoriesService;
    }


    @GetMapping("/{chatroomId}")
    public ResponseEntity<List<Chatroom_Categories>> getChatroomCategories(@PathVariable("chatroomId") Long chatroomId) {
        List<Chatroom_Categories> categories = (List<Chatroom_Categories>) chatroomCategoriesService.getChatroomCategories(chatroomId);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Chatroom_Categories> createChatroomCategories(@RequestBody Chatroom_Categories chatroomCategories) {
        Chatroom_Categories createdCategories = chatroomCategoriesService.createChatroomCategories(chatroomCategories);
        return ResponseEntity.ok(createdCategories);
    }

    @PutMapping("/{chatroomId}/{categoryId}")
    public ResponseEntity<Void> updateChatroomCategories(
            @PathVariable("chatroomId") Long chatroomId,
            @PathVariable("categoryId") Long categoryId) {
        chatroomCategoriesService.updateChatroomCategories(chatroomId, categoryId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{chatroomId}/{categoryId}")
    public ResponseEntity<Void> deleteChatroomCategories(
            @PathVariable("chatroomId") Long chatroomId,
            @PathVariable("categoryId") Long categoryId) {
        chatroomCategoriesService.deleteChatroomCategories(chatroomId, categoryId);
        return ResponseEntity.noContent().build();
    }
}
