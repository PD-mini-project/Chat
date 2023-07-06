package com.chatting.room.user.domain;

import com.chatting.room.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    @Length(max = 20)
    private String username;

    @Column(name = "password", nullable = false)
    @Length(max = 255)
    private String password;

    @Column(name = "description")
    @Length(max = 255)
    private String description;

    public User(Long id, String username, String password, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.description = description;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateDescription(String description) {
        this.description = description;
    }
}
