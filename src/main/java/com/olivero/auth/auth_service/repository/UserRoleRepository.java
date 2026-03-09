package com.olivero.auth.auth_service.repository;

import com.olivero.auth.auth_service.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
}
