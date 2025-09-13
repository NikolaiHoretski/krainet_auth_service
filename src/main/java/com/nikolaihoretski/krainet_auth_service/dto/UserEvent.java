package com.nikolaihoretski.krainet_auth_service.dto;

import java.io.Serializable;

public class UserEvent implements Serializable {

    private String username;
    private String email;
    private String password;
    private String operationType;

    public UserEvent() {
    }

    public UserEvent(String username, String email, String password, String operationType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.operationType = operationType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", operationType='" + operationType + '\'' +
                '}';
    }
}
