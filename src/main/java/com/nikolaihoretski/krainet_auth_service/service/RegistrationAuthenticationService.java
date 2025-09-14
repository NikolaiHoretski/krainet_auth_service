package com.nikolaihoretski.krainet_auth_service.service;

import com.nikolaihoretski.krainet_auth_service.dto.UserJWTDTO;
import com.nikolaihoretski.krainet_auth_service.model.*;
import com.nikolaihoretski.krainet_auth_service.repository.AuthorityRepository;
import com.nikolaihoretski.krainet_auth_service.repository.UserRepository;
import com.nikolaihoretski.krainet_auth_service.security.AuthFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RegistrationAuthenticationService {

    Logger logger = LoggerFactory.getLogger(RegistrationAuthenticationService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthFacade authFacade;
    @Autowired
    private EventPublisherSendEmail eventPublisherSendEmail;
    @Autowired
    private EventPublisherGetEmail eventPublisherGetEmail;

    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User already exist");
        }
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);

        Authority authority = new Authority();

        authority.setUsername(request.getUsername());

        authority.setAuthority(Role.ROLE_USER.name());

        authorityRepository.save(authority);

        eventPublisherSendEmail.publishEventSendEmail(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                OperationType.CREATE.name()
        );
        if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(Role.ROLE_ADMIN.name()))) {
           eventPublisherGetEmail.publisherEventGetEmail(List.of(user.getEmail()));
        }
        logger.info("Пользователь с username '{}' добавлен", user.getUsername());
    }

    public String verify(RegisterRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(), request.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("user not found"));

        UserJWTDTO dto = new UserJWTDTO(user);

        if (authentication.isAuthenticated()) {
            return jwtService.generatedToken(request.getUsername(), userDetails, dto);
        }
        return "verify is failed";
    }
}
