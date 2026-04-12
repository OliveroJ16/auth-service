package com.olivero.auth.auth_service.service;

import com.olivero.auth.auth_service.dto.request.RegisterRequest;
import com.olivero.auth.auth_service.dto.response.UserResponse;

public interface UserService {
    UserResponse saveUser (RegisterRequest registerRequest);
}
