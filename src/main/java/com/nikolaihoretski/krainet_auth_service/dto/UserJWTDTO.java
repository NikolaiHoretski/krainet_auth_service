package com.nikolaihoretski.krainet_auth_service.dto;

import com.nikolaihoretski.krainet_auth_service.model.User;

public class UserJWTDTO {

    private final String firstname;
    private final String lastname;


    public UserJWTDTO(User user) {
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
