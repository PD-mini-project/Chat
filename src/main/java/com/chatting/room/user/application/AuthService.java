package com.chatting.room.user.application;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean isAuthor(Long paramId, Long userId) {
        return true;
    }
}
