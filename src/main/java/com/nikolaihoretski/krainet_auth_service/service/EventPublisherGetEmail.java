package com.nikolaihoretski.krainet_auth_service.service;

import com.nikolaihoretski.krainet_auth_service.config.RabbitConfig;
import com.nikolaihoretski.krainet_auth_service.dto.EmailList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventPublisherGetEmail {

    Logger logger = LoggerFactory.getLogger(EventPublisherGetEmail.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publisherEventGetEmail(List<String> emailList) {

        EmailList list = new EmailList(emailList);

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY_GET_EMAIL, list);
    }
}
