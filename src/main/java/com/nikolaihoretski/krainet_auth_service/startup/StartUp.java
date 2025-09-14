package com.nikolaihoretski.krainet_auth_service.startup;

import com.nikolaihoretski.krainet_auth_service.service.EventPublisherGetEmail;
import com.nikolaihoretski.krainet_auth_service.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartUp {

    Logger logger = LoggerFactory.getLogger(StartUp.class);

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EventPublisherGetEmail eventPublisherGetEmail;

    @EventListener(ApplicationReadyEvent.class)
    public void once() {
        List<String> emails = userService.findAdminEmails();
        eventPublisherGetEmail.publisherEventGetEmail(emails);
    }
}
