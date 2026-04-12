package com.olivero.auth.auth_service.dto.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        String username,
        boolean enabled,
        Set<String> roles,
        LocalDateTime createdAt

) { }
