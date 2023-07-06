package com.chatting.room.chatroom.repository;

import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.user.domain.User;
import com.chatting.room.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChatRoomRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    private User getUser() {
        return User.builder()
                .username("username")
                .password("password")
                .build();
    }

    private ChatRoom getChatRoom(User savedUser) {
        return ChatRoom.builder()
                .title("title")
                .description("description")
                .user(savedUser)
                .build();
    }

    private List<ChatRoom> getChatRoomList(User savedUser) {
        List<ChatRoom> chatRoomList = new ArrayList<>();

        ChatRoom chatRoom1 = ChatRoom.builder()
                .title("title1")
                .description("description1")
                .user(savedUser)
                .build();

        ChatRoom chatRoom2 = ChatRoom.builder()
                .title("title2")
                .description("description2")
                .user(savedUser)
                .build();

        chatRoomList.add(chatRoom1);
        chatRoomList.add(chatRoom2);

        return chatRoomList;
    }

    @Test
    @DisplayName("채팅방 생성 성공")
    void createChatRoom() {
        // given
        User user = getUser();
        User savedUser = userRepository.save(user);

        // when
        ChatRoom chatRoom = getChatRoom(savedUser);

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        // then
        assertThat(savedChatRoom).isSameAs(chatRoom);
    }

    @Test
    @DisplayName("id로 채팅방 조회")
    void findChatRoomById() {
        // given
        User user = getUser();
        User savedUser = userRepository.save(user);

        ChatRoom chatRoom = getChatRoom(savedUser);

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        testEntityManager.flush();
        testEntityManager.clear();

        // when
        ChatRoom findChatRoom = chatRoomRepository.findById(1L)
                .orElseThrow(NoSuchElementException::new);

        // Then
        assertThat(findChatRoom.getId()).isSameAs(savedChatRoom.getId());
        assertThat(findChatRoom.getTitle()).isEqualTo(savedChatRoom.getTitle());
    }

    @Test
    @DisplayName("채팅방 호스트 id 검증")
    void checkChatRoomUserId() {
        // given
        User user = getUser();
        User savedUser = userRepository.save(user);

        ChatRoom chatRoom = getChatRoom(savedUser);

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        testEntityManager.flush();
        testEntityManager.clear();

        // when
        ChatRoom findChatRoom = chatRoomRepository.findById(1L)
                .orElseThrow(NoSuchElementException::new);

        // Then
        assertThat(findChatRoom.getUser().getId()).isEqualTo(savedUser.getId());
    }

    @Test
    @DisplayName("채팅방 리스트 조회")
    void chatRoomList() {
        // given
        User user1 = getUser();
        User savedUser = userRepository.save(user1);

        List<ChatRoom> chatRoomList = getChatRoomList(savedUser);

        List<ChatRoom> savedChatRoomList = chatRoomRepository.saveAllAndFlush(chatRoomList);

        testEntityManager.clear();

        // when
        List<ChatRoom> findChatRoomList = chatRoomRepository.findAll();

        // then
        assertThat(findChatRoomList).hasSize(savedChatRoomList.size());
    }
}