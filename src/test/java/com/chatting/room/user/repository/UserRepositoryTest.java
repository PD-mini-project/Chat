package com.chatting.room.user.repository;

import com.chatting.room.user.domain.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    private User getUser() {
        return User.builder()
                .username("test")
                .password("test")
                .build();
    }

    @Test
    @DisplayName("사용자 생성 성공")
    void findUserById() {
        // given
        User user = getUser();

        // when
        User savedUser = userRepository.save(user);

        testEntityManager.flush();
        testEntityManager.clear();

        // then
        assertThat(savedUser).isSameAs(user);
    }

    @Test
    @DisplayName("id로 user 조회")
    void findUserByUsername() {
        // given
        User user = getUser();
        User savedUser = userRepository.save(user);

        testEntityManager.flush();

        // when
        Optional<User> findUser = userRepository.findById(savedUser.getId());

        assertThat(findUser).containsSame(savedUser);
    }
}