package com.olivero.auth.auth_service.mapper;

import com.olivero.auth.auth_service.dto.request.RegisterRequest;
import com.olivero.auth.auth_service.dto.response.UserResponse;
import com.olivero.auth.auth_service.model.User;

public interface UserMapper {
    User toEntity(RegisterRequest request);
    UserResponse toResponse(User user);
}
