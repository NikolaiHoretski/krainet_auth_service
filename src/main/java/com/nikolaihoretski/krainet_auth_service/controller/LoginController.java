package com.nikolaihoretski.krainet_auth_service.controller;

import com.nikolaihoretski.krainet_auth_service.model.RegisterRequest;
import com.nikolaihoretski.krainet_auth_service.service.RegistrationAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/krainet/")
public class LoginController {

    @Autowired
    private RegistrationAuthenticationService registrationService;

    @PostMapping("/login")
    public String login(@RequestBody RegisterRequest request) {
        return registrationService.verify(request);
    }

}
