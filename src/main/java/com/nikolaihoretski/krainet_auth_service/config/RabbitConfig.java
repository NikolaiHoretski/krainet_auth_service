package com.nikolaihoretski.krainet_auth_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "event.exchange";
    public static final String ROUTING_KEY = "routing.key";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }

//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
//        converter.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.INFERRED);
//        return converter;
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(jsonMessageConverter());
//        return template;
//    }
}
