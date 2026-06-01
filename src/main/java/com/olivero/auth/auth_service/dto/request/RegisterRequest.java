package com.olivero.auth.auth_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for user registration")
public record RegisterRequest(

        @Schema(
                description = "User email address",
                example = "john.doe@example.com"
        )
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 150, message = "Email must not exceed 150 characters")
        String email,

        @Schema(
                description = "Unique username",
                example = "john_doe"
        )
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters")
        String username,

        @Schema(
                description = "Account password",
                example = "StrongPassword123"
        )
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 255, message = "Password must be at least 8 characters")
        String password
) { }
