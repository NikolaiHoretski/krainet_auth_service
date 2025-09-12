package com.nikolaihoretski.krainet_auth_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserResponse {

    @JsonProperty("value")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
