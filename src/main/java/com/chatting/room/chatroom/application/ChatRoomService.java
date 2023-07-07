package com.chatting.room.chatroom.application;


import com.chatting.room.chatroom.domain.Categories;
import com.chatting.room.chatroom.domain.Category;
import com.chatting.room.chatroom.domain.ChatRoom;

import com.chatting.room.chatroom.dto.request.CreateChatRoomRequest;
import com.chatting.room.chatroom.dto.request.UpdateChatRoomRequest;
import com.chatting.room.chatroom.dto.response.CategoryResponse;
import com.chatting.room.chatroom.dto.response.ChatRoomResponse;

import com.chatting.room.chatroom.execption.ChatRoomDescriptionLengthException;
import com.chatting.room.chatroom.execption.ChatRoomTitleLengthException;
import com.chatting.room.chatroom.execption.ChatRoomTitleNullException;
import com.chatting.room.chatroom.repository.CategoryRepository;
import com.chatting.room.chatroom.repository.ChatRoomRepository;
import com.chatting.room.user.domain.User;
import com.chatting.room.user.exception.UserNotFoundException;
import com.chatting.room.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    private static final int TITLE_LENGTH = 255;
    private static final int DESCRIPTION_LENGTH = 255;

    @Transactional
    public ChatRoomResponse createChatRoom(CreateChatRoomRequest request, Long userId) {
        // for fk
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        String title = request.getTitle();
        String description = request.getDescription();
        String categoryVal = request.getCategories();

        validateTitle(title);
        validateDescription(description);
        Categories categories = Categories.toCategory(categoryVal);

        ChatRoom chatRoom = CreateChatRoomRequest.toEntity(request, user);

        ChatRoom saveChatRoom = chatRoomRepository.save(chatRoom);
        List<Category> savedCategoryList = categoryRepository.saveAll(categories.getCategories());

        return ChatRoomResponse.from(user, saveChatRoom, savedCategoryList);
    }

    public void deleteChatRoom(Long chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
    }

    public ChatRoomResponse updateChatRoom(Long chatRoomId, UpdateChatRoomRequest request) {
        return null;
    }

    public List<ChatRoomResponse> chatRoomList() {
        return null;
    }

    private List<ChatRoomResponse> convertToDtoList(List<ChatRoom> chatRooms) {
        return null;
    }

    private void validateTitle(String title) {
        if (Objects.isNull(title)) {
            throw new ChatRoomTitleNullException();
        }

        if (title.length() > TITLE_LENGTH) {
            throw new ChatRoomTitleLengthException();
        }
    }

    private void validateDescription(String description) {
        if (description.length() > DESCRIPTION_LENGTH) {
            throw new ChatRoomDescriptionLengthException();
        }
    }
}
