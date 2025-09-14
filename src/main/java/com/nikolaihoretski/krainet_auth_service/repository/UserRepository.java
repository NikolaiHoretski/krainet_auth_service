package com.nikolaihoretski.krainet_auth_service.repository;

import com.nikolaihoretski.krainet_auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String name);
    @Query("SELECT u.email FROM User u JOIN u.authorities a WHERE a.authority = 'ROLE_ADMIN'")
    List<String> findUsersByRoles(String role);
}
