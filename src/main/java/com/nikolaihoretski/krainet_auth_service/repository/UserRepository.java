package com.nikolaihoretski.krainet_auth_service.repository;

import com.nikolaihoretski.krainet_auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String name);
}
