package com.chatting.room.chatroom.repository;

import com.chatting.room.chatroom.domain.ChatRoom;
import com.chatting.room.user.domain.User;
import com.chatting.room.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChatRoomRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    private UserRepository userRepository;

    private User getUser() {
        return User.builder()
                .username("username")
                .password("password")
                .build();
    }

    @Test
    @DisplayName("채팅방 생성 ")
    void createChatRoom(){
        //given
        User user = getUser();
        User savedUser = userRepository.save(user);
        testEntityManager.flush();

        //when
        ChatRoom chatRoom = ChatRoom.builder()
                .title("title")
                .description("description")
                .user(savedUser)
                .build();
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        //then
        assertThat(savedChatRoom).isSameAs(chatRoom);

    }

}