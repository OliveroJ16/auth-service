package com.olivero.auth.auth_service.repository;

import com.olivero.auth.auth_service.model.User;
import com.olivero.auth.auth_service.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    List<UserRole> findByUser(User user);

}
