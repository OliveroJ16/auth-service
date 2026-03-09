package com.olivero.auth.auth_service.repository;

import com.olivero.auth.auth_service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
