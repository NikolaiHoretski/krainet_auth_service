package com.nikolaihoretski.krainet_auth_service.controller;

import com.nikolaihoretski.krainet_auth_service.model.RegisterRequest;
import com.nikolaihoretski.krainet_auth_service.model.User;
import com.nikolaihoretski.krainet_auth_service.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/krainet/")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/admin/{username}")
    public ResponseEntity<User> getUserById(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserById(username));
    }

    @GetMapping("/admin/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/admin/{username}")
    public User updateUser(@PathVariable String username, @RequestBody Map<String, Object> updates) {
        return userService.updateUserField(username, updates);
    }

    @DeleteMapping("/admin/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}