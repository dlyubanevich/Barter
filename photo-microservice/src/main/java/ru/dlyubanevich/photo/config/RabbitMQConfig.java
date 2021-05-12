package ru.dlyubanevich.photo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "barter-exchange";
    public static final String USER_ACTIVITY_QUEUE = "user-activity-queue";
    public static final String NOMENCLATURE_ACTIVITY_QUEUE = "nomenclature-activity-queue";

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(EXCHANGE_NAME);
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue userActivityQueue() {
        return new Queue(USER_ACTIVITY_QUEUE);
    }

    @Bean
    public Queue nomenclatureActivityQueue(){
        return new Queue(NOMENCLATURE_ACTIVITY_QUEUE);
    }

    @Bean
    public Binding photoActivityBinding(){
        return BindingBuilder
                .bind(userActivityQueue())
                .to(directExchange())
                .with("photo.user");
    }

    @Bean
    public Binding nomenclatureActivityBinding(){
        return BindingBuilder
                .bind(nomenclatureActivityQueue())
                .to(directExchange())
                .with("photo.nomenclature");
    }

}
