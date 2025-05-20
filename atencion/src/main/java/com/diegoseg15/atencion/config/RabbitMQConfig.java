package com.diegoseg15.atencion.config;

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
    public Queue leveQueue() {
        return new Queue("pacientes.leve");
    }

    @Bean
    public Queue graveQueue() {
        return new Queue("pacientes.grave");
    }

    @Bean
    public Queue criticoQueue() {
        return new Queue("pacientes.critico");
    }

    @Bean
    public Binding bindingLeve(Queue leveQueue, TopicExchange exchange) {
        return BindingBuilder.bind(leveQueue).to(exchange).with("pacientes.leve");
    }

    @Bean
    public Binding bindingGrave(Queue graveQueue, TopicExchange exchange) {
        return BindingBuilder.bind(graveQueue).to(exchange).with("pacientes.grave");
    }

    @Bean
    public Binding bindingCritico(Queue criticoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(criticoQueue).to(exchange).with("pacientes.critico");
    }
}
