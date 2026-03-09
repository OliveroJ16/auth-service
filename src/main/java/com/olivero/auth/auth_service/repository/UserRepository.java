package com.olivero.auth.auth_service.repository;

import com.olivero.auth.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID> {
}
