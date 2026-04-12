package com.olivero.auth.auth_service.mapper;

import com.olivero.auth.auth_service.dto.request.RegisterRequest;
import com.olivero.auth.auth_service.dto.response.UserResponse;
import com.olivero.auth.auth_service.model.User;

import java.util.Set;

public interface UserMapper {
    User toEntity(RegisterRequest request);
    UserResponse toResponse(User user, Set<String> roles);
}
