package com.chatting.room.user.application;

import com.chatting.room.user.domain.User;
import com.chatting.room.user.dto.request.UserLoginDto;
import com.chatting.room.user.dto.request.UserReq;
import com.chatting.room.user.dto.response.UserRespDto;
import com.chatting.room.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public UserService(UserRepository userRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }

    public void registerUser(UserReq userReq) {
        User user = new User(null, userReq.getUsername(), userReq.getPassword(), userReq.getDescription());
        userRepository.save(user);
    }

    public boolean withdrawUser() {
        String username = (String) httpSession.getAttribute("username");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            httpSession.removeAttribute("username");
            return true;
        }
        return false;
    }


    public boolean loginUser(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
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

    public UserRespDto getUserInfo() {
        String username = (String) httpSession.getAttribute("username");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(user -> new UserRespDto(user.getUsername(), user.getDescription()))
                .orElse(null);
    }

    public void updateUserInfo(UserReq userReq) {
        String username = (String) httpSession.getAttribute("username");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        optionalUser.ifPresent(user -> {
            user.setPassword(userReq.getPassword());
            user.setDescription(userReq.getDescription());
        });
    }

    public List<UserRespDto> getUserList() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserRespDto(user.getUsername(), user.getDescription()))
                .collect(Collectors.toList());
    }


    public void resetPassword(String username, String newPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }
}