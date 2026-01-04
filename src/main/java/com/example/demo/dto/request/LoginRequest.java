package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(@NotBlank @Size(min=5,max = 50)  String email,
                           @NotBlank @Size(min=6,max = 50)  String password
) {
}
