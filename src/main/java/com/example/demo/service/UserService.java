package com.example.demo.service;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.request.UpdateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "The user was not found"));

        return new UserResponse(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponse(user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()
                ))
                .toList();

    }

    public void deleteUser(long userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "The user was not found"));

        userRepository.deleteById(userId);

    }

    public UserResponse modifyUser(long userId,UpdateUserRequest request) {

        User modifiedUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "The user was not found"));

        if(request.firstName() != null){
            modifiedUser.setFirstName(request.firstName());
        }
        if(request.lastName() != null){
            modifiedUser.setLastName(request.lastName());
        }
        if(request.email() != null){
            modifiedUser.setEmail(request.email());
        }
        if(request.password() != null){
            modifiedUser.setPassword(request.password());
        }

        User savedUser = userRepository.save(modifiedUser);

        return new UserResponse(savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail());
    }
}
