package com.chatting.room.common.redis;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisSessionService {

    private final HttpSession httpSession;

    public RedisSessionService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }


    public void saveUserInfo(String userId) {
        log.info("saveUserId");
        httpSession.setAttribute("userId", userId);
    }

    public Object getUserInfo() {
        log.info("getUserId");
        return httpSession.getAttribute("userId");
    }

    public void deleteUserInfo() {
        log.info("deleteUserId");
        httpSession.removeAttribute("userId");
    }
}
