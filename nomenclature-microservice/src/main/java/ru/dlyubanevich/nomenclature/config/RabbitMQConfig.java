package ru.dlyubanevich.nomenclature.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "barter-exchange";
    public static final String PHOTO_ACTIVITY_QUEUE = "photo-activity-queue";
    public static final String ROUTING_KEY_NOMENCLATURE_PHOTO = "nomenclature.photo";

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
    public Binding photoActivityBinding(){
        return BindingBuilder
                .bind(photoActivityQueue())
                .to(directExchange())
                .with(ROUTING_KEY_NOMENCLATURE_PHOTO);
    }
}
