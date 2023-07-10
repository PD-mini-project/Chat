package com.chatting.room.common.aop;

import com.chatting.room.common.exception.UnAuthorizedException;
import com.chatting.room.user.application.AuthService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {
    private final AuthService authService;

    public AuthenticationAspect(AuthService authService) {
        this.authService = authService;
    }

    @Before(value = "@annotation(com.chatting.room.common.aop.Authentication) && args(chatRoomId, .., userId)", argNames = "chatRoomId, userId")
    public void authorizeMaster(Long chatRoomId, Long userId) {
        if (!authService.isAuthor(chatRoomId, userId)) {
            throw new UnAuthorizedException();
        }
    }
}
