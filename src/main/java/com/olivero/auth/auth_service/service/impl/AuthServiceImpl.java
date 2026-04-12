package com.olivero.auth.auth_service.service.impl;

import com.olivero.auth.auth_service.dto.request.LoginRequest;
import com.olivero.auth.auth_service.dto.response.AuthResponse;
import com.olivero.auth.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {
        return null;
    }
}
