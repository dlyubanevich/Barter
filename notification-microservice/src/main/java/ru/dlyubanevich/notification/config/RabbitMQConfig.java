package ru.dlyubanevich.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "barter-exchange";
    public static final String USER_SETTINGS_QUEUE = "user-settings-queue";
    public static final String OFFER_ACTIVITY_QUEUE = "offer-activity-queue";

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(EXCHANGE_NAME);
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue userSettingsQueue() {
        return new Queue(USER_SETTINGS_QUEUE);
    }

    @Bean
    public Queue offerActivityQueue() {
        return new Queue(OFFER_ACTIVITY_QUEUE);
    }

    @Bean
    public Binding userSettingBinding(){
        return BindingBuilder.bind(userSettingsQueue())
                .to(topicExchange())
                .with("notification.user");
    }

    @Bean
    public Binding offerActivityBinding(){
        return BindingBuilder.bind(offerActivityQueue())
                .to(topicExchange())
                .with("notification.offer");
    }
}
