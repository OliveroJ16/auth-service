package com.olivero.auth.auth_service.mapper.impl;

import com.olivero.auth.auth_service.dto.request.RegisterRequest;
import com.olivero.auth.auth_service.dto.response.UserResponse;
import com.olivero.auth.auth_service.mapper.UserMapper;
import com.olivero.auth.auth_service.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(request.password());
        return user;
    }

    @Override
    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.isEnabled(),
                user.getCreatedAt()
        );
    }
}
