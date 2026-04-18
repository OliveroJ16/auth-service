package com.olivero.auth.auth_service.controller;

import com.olivero.auth.auth_service.dto.request.LoginRequest;
import com.olivero.auth.auth_service.dto.request.RegisterRequest;
import com.olivero.auth.auth_service.dto.response.AuthResult;
import com.olivero.auth.auth_service.dto.response.AuthResponse;
import com.olivero.auth.auth_service.dto.response.UserResponse;
import com.olivero.auth.auth_service.security.CookieProvider;
import com.olivero.auth.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CookieProvider cookieProvider;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResult authResult = authService.registerUser(registerRequest);
        ResponseCookie cookie = cookieProvider.createRefreshTokenCookie(authResult.refreshToken());
        var authResponse = new AuthResponse(authResult.accessToken(), authResult.accessTokenExpiresIn(), authResult.user());
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(authResponse);
    }

    @PostMapping("/login")
    public  ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest){
        return null;
    }
}