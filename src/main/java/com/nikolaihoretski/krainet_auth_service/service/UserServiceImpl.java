package com.nikolaihoretski.krainet_auth_service.service;

import com.nikolaihoretski.krainet_auth_service.model.User;
import com.nikolaihoretski.krainet_auth_service.repository.UserRepository;
import com.nikolaihoretski.krainet_auth_service.security.AuthFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthFacade authFacade;

    @Override
    public User getUserById(String username) {

        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found:" + username));

        logger.info("Пользователь: '{}' найден", user.getUsername());

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        logger.info("Всего пользователей: '{}'", users.size());
        users.forEach(user -> logger.info("Пользователь: '{}'", user));

        return users;
    }

    @Override
    public User updateUserField(String username, Map<String, Object> updates) {

        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("user not found"));

        if(updates.containsKey("firstname")) {
            user.setFirstname((String) updates.get("firstname"));
        }
        if (updates.containsKey("lastname")) {
         user.setLastname((String) updates.get("lastname"));
        }
        if(updates.containsKey("password")) {
            user.setPassword("password");
        }
        if(updates.containsKey("email")) {
            user.setEmail((String) updates.get("email"));
        }

        if(authFacade.getCurrentRole().contains("ROLE_ADMIN")) {
            if (updates.containsKey("enabled")) {
                Object enableValue = updates.get("enabled");
                boolean boolValue;

                if (enableValue instanceof Boolean) {
                    boolValue = (Boolean) enableValue;
                } else if (enableValue instanceof String) {
                    boolValue = Boolean.parseBoolean((String) enableValue);
                } else {
                    throw new IllegalArgumentException("Неверный тип данных для поля enabled");
                }
                user.setEnabled(boolValue);
            }
        }

        logger.info("Учетные данные Пользователя: {} обнавлены", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        userRepository.deleteById(username);
        logger.info("Пользователь: {} удален", user.getUsername());
    }
}
