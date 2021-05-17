package ru.dlyubanevich.user.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "barter-exchange";
    public static final String PHOTO_ACTIVITY_QUEUE = "photo-activity-queue";
    public static final String NOMENCLATURE_ACTIVITY_QUEUE = "nomenclature-activity-queue";
    private static final String ROUTING_KEY_USER_AVATAR = "user.avatar";
    private static final String ROUTING_KEY_USER_ITEMS = "user.items";

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
    public Queue photoActivityQueue() {
        return new Queue(PHOTO_ACTIVITY_QUEUE);
    }

    @Bean
    public Queue nomenclatureActivityQueue(){
        return new Queue(NOMENCLATURE_ACTIVITY_QUEUE);
    }

    @Bean
    public Binding photoActivityBinding(){
        return BindingBuilder
                .bind(photoActivityQueue())
                .to(directExchange())
                .with(ROUTING_KEY_USER_AVATAR);
    }

    @Bean
    public Binding nomenclatureActivityBinding(){
        return BindingBuilder
                .bind(nomenclatureActivityQueue())
                .to(directExchange())
                .with(ROUTING_KEY_USER_ITEMS);
    }


}
