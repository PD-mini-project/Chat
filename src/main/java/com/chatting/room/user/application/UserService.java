package com.chatting.room.user.application;

import com.chatting.room.user.dto.response.UserRespDto;
import com.chatting.room.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 샘플
    public List<UserRespDto> userList() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserRespDto(user.getUsername(), user.getDescription()))
                .collect(Collectors.toList());
    }
}
