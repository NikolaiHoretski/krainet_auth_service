package com.nikolaihoretski.krainet_auth_service.repository;

import com.nikolaihoretski.krainet_auth_service.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

    List<Authority> findByUsername(String username);
}
