package com.olivero.auth.auth_service.service.impl;

import com.olivero.auth.auth_service.dto.request.LoginRequest;
import com.olivero.auth.auth_service.dto.request.RegisterRequest;
import com.olivero.auth.auth_service.dto.response.AuthResult;
import com.olivero.auth.auth_service.dto.response.AuthResponse;
import com.olivero.auth.auth_service.dto.response.UserResponse;
import com.olivero.auth.auth_service.model.RefreshToken;
import com.olivero.auth.auth_service.repository.RefreshTokenRepository;
import com.olivero.auth.auth_service.repository.UserRepository;
import com.olivero.auth.auth_service.security.JwtTokenProvider;
import com.olivero.auth.auth_service.service.AuthService;
import com.olivero.auth.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Override
    public AuthResult registerUser(RegisterRequest registerRequest){
        UserResponse user = userService.saveUser(registerRequest);
        String accessToken = jwtTokenProvider.generateAccessToken(user, user.roles());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);
        saveRefreshToken(user, refreshToken);
        return new AuthResult(accessToken, refreshToken, jwtExpiration, user);
    }

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {
        //Terminar despues...
        return null;
    }

    private void saveRefreshToken(UserResponse userResponse, String tokenString) {
        var user = userRepository.findById(userResponse.id()).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials or account not found"));
        LocalDateTime expiresAt = LocalDateTime.now().plus(Duration.ofMillis(refreshExpiration));
        var refreshToken = RefreshToken.builder()
                .token(tokenString)
                .user(user)
                .revoked(false)
                .expiresAt(expiresAt)
                .build();
        refreshTokenRepository.save(refreshToken);
    }
}
