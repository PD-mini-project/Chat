package com.chatting.room.user.application;

import com.chatting.room.user.domain.User;
import com.chatting.room.user.dto.request.UserLoginRequest;
import com.chatting.room.user.dto.request.UserCreateRequest;
import com.chatting.room.user.dto.response.UserResponse;
import com.chatting.room.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public UserService(UserRepository userRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }

    @Transactional
    public void registerUser(UserCreateRequest userReq) {
        User user = new User(null, userReq.getUsername(), userReq.getPassword(), userReq.getDescription());
        userRepository.save(user);
    }

    public boolean loginUser(UserLoginRequest userLoginRequest) {
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                httpSession.setAttribute("username", username);
                return true;
            }
        }
        return false;
    }

    public UserResponse getUserInfo() {
        String username = (String) httpSession.getAttribute("username");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(user -> new UserResponse(user.getUsername(), user.getDescription()))
                .orElse(null);
    }

    @Transactional
    public void updateUserInfo(UserCreateRequest userReq) {
        String username = (String) httpSession.getAttribute("username");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        optionalUser.ifPresent(user -> {
            user.updatePassword(userReq.getPassword());
            user.updateDescription(userReq.getDescription());
        });
    }

    public List<UserResponse> getUserList() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getUsername(), user.getDescription()))
                .collect(Collectors.toList());
    }
}