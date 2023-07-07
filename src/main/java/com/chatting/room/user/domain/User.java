package com.chatting.room.user.domain;

import com.chatting.room.common.domain.BaseEntity;
import com.chatting.room.user.exception.UserLoginException;
import jakarta.persistence.*;
import lombok.*;

import static lombok.EqualsAndHashCode.*;


@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "description")
    private String description;

    public User(Long id, String username, String password, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.description = description;
    }

    public void updateUserInfo(String username, String password, String description) {
        this.username = username;
        this.password = password;
        this.description = description;
    }

    public void isSameUsernameAndPassword(String username, String password) {
        if (!this.username.equals(username) || !this.password.equals(password)) {
            throw new UserLoginException();
        }
    }

    public boolean isSameUser(Long userId) {
        return id.equals(userId);
    }
}
