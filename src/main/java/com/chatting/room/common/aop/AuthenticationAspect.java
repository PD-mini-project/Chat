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

    @Before("@annotation(com.chatting.room.common.aop.Authentication) && args(paramId, .., loginId)")
    public void authorizeMaster(Long paramId, Long loginId) {
        if (!authService.isAuthor(paramId, loginId)) {
            throw new UnAuthorizedException();
        }
    }
}
