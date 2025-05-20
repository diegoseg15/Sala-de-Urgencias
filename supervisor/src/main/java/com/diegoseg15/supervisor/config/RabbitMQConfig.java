package com.diegoseg15.supervisor.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "triaje-exchange";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue supervisorQueue() {
        return new Queue("pacientes.supervisor");
    }

    @Bean
    public Binding bindingSupervisor(Queue supervisorQueue, TopicExchange exchange) {
        return BindingBuilder.bind(supervisorQueue).to(exchange).with("pacientes.*");
    }
}
