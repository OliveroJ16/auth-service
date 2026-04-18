package com.olivero.auth.auth_service.service;

import com.olivero.auth.auth_service.dto.request.LoginRequest;
import com.olivero.auth.auth_service.dto.request.RegisterRequest;
import com.olivero.auth.auth_service.dto.response.AuthResult;
import com.olivero.auth.auth_service.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse loginUser(LoginRequest loginRequest);
    AuthResult registerUser(RegisterRequest registerRequest);
}
