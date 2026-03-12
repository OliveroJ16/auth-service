package com.olivero.auth.auth_service.controller;

import com.olivero.auth.auth_service.dto.request.UserRequest;
import com.olivero.auth.auth_service.dto.response.UserResponse;
import com.olivero.auth.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * configurar despues el securityConfig
     */
}