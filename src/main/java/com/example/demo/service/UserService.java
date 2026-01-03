package com.example.demo.service;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserResponse createUser(CreateUserRequest request) {
        User user = new User(request.firstName(),
                request.lastName(),
                request.email(),
                request.password());

        User userSaved = userRepository.save(user);

        return new UserResponse(userSaved.getId(),
                userSaved.getFirstName(),
                userSaved.getLastName(),
                userSaved.getEmail());
    }

    public UserResponse getUser(long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("The user was not found"));

        return new UserResponse(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }
}
