package com.olivero.auth.auth_service.dto.response;

import org.springframework.http.ResponseCookie;

public record AuthResult(
        String accessToken,
        String refreshToken,
        Long accessTokenExpiresIn,
        UserResponse user
) {
}
