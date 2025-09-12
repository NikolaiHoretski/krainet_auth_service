package com.nikolaihoretski.krainet_auth_service.controller;

import com.nikolaihoretski.krainet_auth_service.model.User;
import com.nikolaihoretski.krainet_auth_service.security.AuthFacade;
import com.nikolaihoretski.krainet_auth_service.service.RegistrationAuthenticationService;
import com.nikolaihoretski.krainet_auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/krainet/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthFacade authFacade;

    @Autowired
    private RegistrationAuthenticationService registrationService;

    @GetMapping("/user/greeting")
    public String response(Authentication auth) {
        return "Привет: " + auth.getName();
    }


    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserById(@PathVariable String username) {

        if (!authFacade.getCurrentUsername().equals(username))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.ok(userService.getUserById(username));
    }

    @PatchMapping("/user/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody Map<String, Object> updates) {

        if (!authFacade.getCurrentUsername().equals(username))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        userService.updateUserField(username, updates);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        if (!authFacade.getCurrentUsername().equals(username))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        userService.deleteUser(username);
        return ResponseEntity.ok("Deleted");
    }
}
