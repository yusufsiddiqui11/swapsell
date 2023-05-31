package com.stackroute.authenticationservice.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MessageConfiguration {
    public static final String exchangeName1 = "swap-sell-user-service-exchange";
    public static final String queueName1 = "swap-sell-user-service-queue";
    public static final String routingKey1 = "swap-sell-user-service-routing-key";
    public static final String exchangeName2 = "second-service-exchange";
    public static final String routingKey2 = "second-service-routing-key";

    public static final String exchangeName3 = "payment-service-exchange";
    public static final String routingKey3 = "payment-service-routing-key";

    @Bean
    @Primary
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName1);
    }

    @Bean
    public Queue queue() {
        return new Queue(queueName1);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey1);
    }

    @Bean
    public DirectExchange secondExchange() {
        return new DirectExchange(exchangeName2);
    }

    @Bean
    public Binding secondBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey2);
    }

    @Bean
    public DirectExchange thirdExchange() {
        return new DirectExchange(exchangeName3);
    }

    @Bean
    public Binding thirdBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey3);
    }
    @Bean
    public Jackson2JsonMessageConverter userJackson2JsonMessage() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(userJackson2JsonMessage());
        return rabbitTemplate;
    }
}
