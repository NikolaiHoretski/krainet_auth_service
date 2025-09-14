package com.nikolaihoretski.krainet_auth_service.service;

import com.nikolaihoretski.krainet_auth_service.config.RabbitConfig;
import com.nikolaihoretski.krainet_auth_service.dto.UserEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherSendEmail {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishEvent(String username, String email, String password, String operationType) {

        UserEvent event = new UserEvent(username, email, password, operationType);

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY_SEND_EMAIL, event);
    }
}
