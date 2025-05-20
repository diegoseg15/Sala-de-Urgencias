package com.diegoseg15.urgencias.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    // @Bean
    // public Queue createUserRegistrationQueue() {
    // return new Queue("q.notifications");
    // }

    public static final String EXCHANGE_NAME = "triaje-exchange";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

}
