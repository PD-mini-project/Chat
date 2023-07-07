package com.chatting.room.user.application;

import com.chatting.room.common.exception.UnAuthorizedException;
import com.chatting.room.common.redis.RedisService;
import com.chatting.room.user.domain.User;
import com.chatting.room.user.dto.request.UserLoginRequest;
import com.chatting.room.user.dto.request.UserCreateRequest;
import com.chatting.room.user.dto.request.UserUpdateRequest;
import com.chatting.room.user.dto.response.UserResponse;
import com.chatting.room.user.exception.*;
import com.chatting.room.user.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final RedisService redisService;

    public UserService(UserRepository userRepository, com.chatting.room.common.redis.RedisService redisService) {
        this.userRepository = userRepository;
        this.redisService = redisService;
    }

    private static final int USERNAME_LENGTH = 20;
    private static final int PASSWORD_LENGTH = 255;
    private static final int DESCRIPTION_LENGTH = 255;

    // 저장된 유저의 pk값 반환
    @Transactional
    public Long createUser(UserCreateRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String description = request.getDescription();

        validateUsername(username);
        validateUsernameDuplicate(username);
        validatePassword(password);
        validateDescription(description);

        User user = UserCreateRequest.toEntity(request);

        return userRepository.save(user).getId();
    }

    public UserResponse loginUser(UserLoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        user.isSameUsernameAndPassword(username, password);

        // Redis에 사용자 정보 저장
        redisService.saveUserInfo(user.getId().toString(), Map.of("id", user.getId(), "username", user.getUsername()));

        return UserResponse.from(user);
    }

    @Transactional
    public Long deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        checkAuthorize(userId, user);

        userRepository.deleteById(user.getId());

        return user.getId();
    }

    public UserResponse userInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserResponse.from(user);
    }

    @Transactional
    public void updateUserInfo(UserUpdateRequest request) {
        Long userId = request.getId();
        String newUsername = request.getNewUsername();
        String newPassword = request.getNewPassword();
        String newDescription = request.getNewDescription();

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        checkAuthorize(userId, user);

        validateUsername(newUsername);
        validateUsernameDuplicate(newUsername);
        validatePassword(newPassword);
        validateDescription(newDescription);

        user.updateUserInfo(newUsername, newPassword, newDescription);
    }

    public List<UserResponse> getUserList() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    private void validateUsername(String username) {
        if (Objects.isNull(username)) {
            throw new UserNameNullException();
        }

        if (username.length() > USERNAME_LENGTH) {
            throw new UserNameLengthException();
        }
    }

    private void validateUsernameDuplicate(String username) {
        boolean isExist = userRepository.existsByUsername(username);
        if (isExist) {
            throw new UserNameDuplicateException();
        }
    }

    private void validatePassword(String password) {
        if (Objects.isNull(password)) {
            throw new UserPasswordNullException();
        }

        if (password.length() > PASSWORD_LENGTH) {
            throw new UserPasswordLengthException();
        }
    }

    private void validateDescription(String description) {
        if (description.length() > DESCRIPTION_LENGTH) {
            throw new UserDescriptionLengthException();
        }
    }

    private static void checkAuthorize(Long userId, User user) {
        if (!user.isSameUser(userId)) {
            throw new UnAuthorizedException();
        }
    }
}