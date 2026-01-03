package com.example.demo.dto.response;

public record UserResponse(
        long id,
        String firstName,
        String lastName,
        String email
) {
}
