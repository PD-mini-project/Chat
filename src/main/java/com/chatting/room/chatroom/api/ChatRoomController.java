package com.chatting.room.chatroom.api;

import com.chatting.room.chatroom.application.ChatRoomService;
import com.chatting.room.chatroom.dto.request.CreateChatRoomRequest;

import com.chatting.room.chatroom.dto.request.UpdateChatRoomRequest;
import com.chatting.room.chatroom.dto.response.ChatRoomRespDto;
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

    @GetMapping("/api/chatroom")
    public ResponseEntity<List<ChatRoomRespDto>> getChatRooms() {
        List<ChatRoomRespDto> chatRooms = chatRoomService.getChatRooms();
        return ResponseEntity.ok(chatRooms);
    }

    @PostMapping("/api/chatroom")
    public ResponseEntity<List<ChatRoomRespDto>> createChatRooms(@RequestBody List<CreateChatRoomRequest> requests) {
        List<ChatRoomRespDto> createdChatRooms = chatRoomService.createChatRooms(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChatRooms);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable("id")Long chatRoomId){
        chatRoomService.deleteChatRoom(chatRoomId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<ChatRoomRespDto>> updateChatRoom(
            @PathVariable("id") Long chatRoomId,
            @RequestBody UpdateChatRoomRequest request) {
        ChatRoomRespDto updatedChatRoom = chatRoomService.updateChatRoom(chatRoomId, request);
        return ResponseEntity.ok(Collections.singletonList(updatedChatRoom));
    }
}
