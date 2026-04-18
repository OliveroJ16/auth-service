package com.olivero.auth.auth_service.service.impl;

import com.olivero.auth.auth_service.dto.request.RegisterRequest;
import com.olivero.auth.auth_service.dto.response.UserResponse;
import com.olivero.auth.auth_service.mapper.UserMapper;
import com.olivero.auth.auth_service.model.Role;
import com.olivero.auth.auth_service.model.User;
import com.olivero.auth.auth_service.model.UserRole;
import com.olivero.auth.auth_service.repository.RoleRepository;
import com.olivero.auth.auth_service.repository.UserRepository;
import com.olivero.auth.auth_service.repository.UserRoleRepository;
import com.olivero.auth.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserResponse saveUser (RegisterRequest registerRequest){
        User user = userMapper.toEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        User savedUser = userRepository.saveAndFlush(user);

        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Here...")); //Verificar excepcion

        var userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(defaultRole);

        userRoleRepository.save(userRole);

        return userMapper.toResponse(savedUser, Collections.singleton(defaultRole.getName()));
    }

}
