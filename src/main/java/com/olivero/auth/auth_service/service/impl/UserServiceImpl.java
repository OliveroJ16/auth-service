package com.olivero.auth.auth_service.service.impl;

import com.olivero.auth.auth_service.dto.request.UserRequest;
import com.olivero.auth.auth_service.dto.response.UserResponse;
import com.olivero.auth.auth_service.mapper.UserMapper;
import com.olivero.auth.auth_service.model.User;
import com.olivero.auth.auth_service.repository.UserRepository;
import com.olivero.auth.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse saveUser (UserRequest userRequest){
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

}
