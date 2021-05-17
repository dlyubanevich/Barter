package ru.dlyubanevich.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "barter-exchange";
    public static final String USER_SETTINGS_QUEUE = "user-settings-queue";
    public static final String OFFER_REQUEST_ACTIVITY_QUEUE = "offer-request-activity-queue";
    public static final String OFFER_RESPONSE_ACTIVITY_QUEUE = "offer-response-activity-queue";
    public static final String DEAL_ACTIVITY_QUEUE = "deal-activity-queue";

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
    public Queue userSettingsQueue() {
        return new Queue(USER_SETTINGS_QUEUE);
    }

    @Bean
    public Queue offerRequestActivityQueue() {
        return new Queue(OFFER_REQUEST_ACTIVITY_QUEUE);
    }

    @Bean
    public Queue offerResponseActivityQueue() {
        return new Queue(OFFER_RESPONSE_ACTIVITY_QUEUE);
    }

    @Bean
    public Queue dealActivityQueue() {
        return new Queue(DEAL_ACTIVITY_QUEUE);
    }

    @Bean
    public Binding userSettingBinding(){
        return BindingBuilder.bind(userSettingsQueue())
                .to(directExchange())
                .with("notification.user");
    }

    @Bean
    public Binding offerRequestActivityBinding(){
        return BindingBuilder.bind(offerRequestActivityQueue())
                .to(directExchange())
                .with("notification.offer.request");
    }

    @Bean
    public Binding offerResponseActivityBinding(){
        return BindingBuilder.bind(offerResponseActivityQueue())
                .to(directExchange())
                .with("notification.offer.response");
    }

    @Bean
    public Binding DealActivityBinding(){
        return BindingBuilder.bind(dealActivityQueue())
                .to(directExchange())
                .with("notification.deal");
    }

}
