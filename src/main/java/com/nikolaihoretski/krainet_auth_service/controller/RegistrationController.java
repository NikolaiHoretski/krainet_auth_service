package com.nikolaihoretski.krainet_auth_service.controller;

import com.nikolaihoretski.krainet_auth_service.model.RegisterRequest;
import com.nikolaihoretski.krainet_auth_service.service.RegistrationAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/krainet/")
public class RegistrationController {

    @Autowired
    private RegistrationAuthenticationService registrationService;

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try{
            registrationService.register(request);
            return ResponseEntity.ok("User registered");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
