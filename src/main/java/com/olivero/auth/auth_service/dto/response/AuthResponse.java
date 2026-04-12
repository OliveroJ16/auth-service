package com.olivero.auth.auth_service.dto.response;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        UserResponse user
) { }
