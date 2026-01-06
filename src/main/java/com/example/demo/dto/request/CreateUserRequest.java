package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
      @NotBlank @Size(min=2,max = 50)  String firstName,
      @NotBlank @Size(min=2,max = 50)  String lastName,
      @Email @NotBlank @Size(min = 2, max = 50) String email,
      @NotBlank @Size(min=6,max = 50)  String password
) {
}
