package ru.dlyubanevich.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.models.UserPhoto;
import ru.dlyubanevich.user.models.UserSubscription;

import static ru.dlyubanevich.user.config.RabbitMQConfig.EXCHANGE_NAME;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void sendPhotoMessage(String userId, String photo) {
        String routingKey = "photo.user";
        UserPhoto userPhoto = new UserPhoto(userId, photo);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, objectMapper.writeValueAsString(userPhoto));
    }

    @SneakyThrows
    @Override
    public void sendNotificationMessage(User user) {
        String routingKey = "notification.user";
        UserSubscription userSubscription = new UserSubscription(user);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, objectMapper.writeValueAsString(userSubscription));
    }
}
