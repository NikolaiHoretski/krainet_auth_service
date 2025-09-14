package com.nikolaihoretski.krainet_auth_service.dto;

import java.io.Serializable;
import java.util.List;

public class EmailList implements Serializable {

    private List<String> emails;

    public EmailList() {
    }

    public EmailList(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
