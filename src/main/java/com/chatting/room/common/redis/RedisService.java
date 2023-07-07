package com.chatting.room.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    private Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    private void del(String key) {
        redisTemplate.delete(key);
    }

    private Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public void saveUserInfo(String username, Object userInfo) {
        log.info("saveUserInfo");
        set(username, userInfo);
    }

    public Object getUserInfo(String username) {
        log.info("getUserInfo");
        return get(username);
    }

    public void deleteUserInfo(String username) {
        log.info("deleteUserInfo");
        del(username);
    }

    public Boolean isExist(String username) {
        return exists(username);
    }

}
