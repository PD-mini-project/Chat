package com.chatting.room.common.aop;

import com.chatting.room.common.exception.UnAuthorizedException;
import com.chatting.room.common.redis.RedisSessionService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Slf4j
@Component
public class LoginUserIdResolver implements HandlerMethodArgumentResolver {

    private final RedisSessionService redisSessionService;

    public LoginUserIdResolver(RedisSessionService redisSessionService) {
        this.redisSessionService = redisSessionService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoginUserId.class) != null &&
                Long.class.equals(parameter.getParameterType());
    }

    @Override
    public Long resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("LoginUserIdResolver");

        Object userId = Optional.ofNullable(redisSessionService.getUserInfo())
                .orElseThrow(UnAuthorizedException::new);

        return (Long) userId;
    }

}
