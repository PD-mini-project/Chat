package com.chatting.room.chatroom.api;

import com.chatting.room.chatroom.application.ChatRoomService;
import com.chatting.room.chatroom.dto.request.CreateChatRoomRequest;
import com.chatting.room.chatroom.dto.request.UpdateChatRoomRequest;
import com.chatting.room.chatroom.dto.response.ChatRoomResponse;
import com.chatting.room.common.aop.Authentication;
import com.chatting.room.common.aop.LoginUserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/chatroom")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/create")
    public ResponseEntity<ChatRoomResponse> createChatRoom(
            @RequestBody CreateChatRoomRequest request,
            @LoginUserId Long userId) {

        ChatRoomResponse response = chatRoomService.createChatRoom(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 권한 검증
    @Authentication
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable("id") Long chatRoomId) {
        chatRoomService.deleteChatRoom(chatRoomId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<List<ChatRoomResponse>> updateChatRoom(
            @PathVariable("id") Long chatRoomId,
            @RequestBody UpdateChatRoomRequest request) {
        ChatRoomResponse updatedChatRoom = chatRoomService.updateChatRoom(chatRoomId, request);
        return ResponseEntity.ok(Collections.singletonList(updatedChatRoom));
    }

    @GetMapping("/chatrooms")
    public ResponseEntity<List<ChatRoomResponse>> chatRoomList(@PathVariable Long id) {
        return null;
    }
}
