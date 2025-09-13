package com.nikolaihoretski.krainet_auth_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME_REQUEST_EMAILS = "requestemail.queue";
    private static final String ROUTING_KEY_REQUEST_EMAILS = "requestemail.routing.key.*";

    public static final String QUEUE_NAME_GET_EMAIL = "getemail.queue";
    private static final String ROUTING_KEY_GET_EMAIL = "getemail.routing.key.*";

    public static final String QUEUE_NAME_SEND_EMAIL = "sendemail.queue";
    public static final String EXCHANGE = "sendemail.exchange";
    public static final String ROUTING_KEY_SEND_EMAIL = "sendemail.routing.key.*";

    @Bean
    public Queue requestEmailQueue() {
        return new Queue(QUEUE_NAME_REQUEST_EMAILS, true);
    }

    @Bean
    public Queue getEmailQueue() {
        return new Queue(QUEUE_NAME_GET_EMAIL, true);
    }

    @Bean
    public Queue sendEmailQueue() {
        return new Queue(QUEUE_NAME_SEND_EMAIL, true);
    }

    @Bean
    public TopicExchange sendEmailExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
