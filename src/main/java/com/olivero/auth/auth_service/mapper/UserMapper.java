package com.olivero.auth.auth_service.mapper;

import com.olivero.auth.auth_service.dto.request.UserRequest;
import com.olivero.auth.auth_service.dto.response.UserResponse;
import com.olivero.auth.auth_service.model.User;

public interface UserMapper {
    User toEntity(UserRequest request);
    UserResponse toResponse(User user);
}
