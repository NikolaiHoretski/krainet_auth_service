package com.nikolaihoretski.krainet_auth_service.service;

import com.nikolaihoretski.krainet_auth_service.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUserById(String username);
    List<User> getAllUsers();
    User updateUserField(String username, Map<String, Object> updates);
    void deleteUser(String username);
}