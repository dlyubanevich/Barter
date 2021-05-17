package ru.dlyubanevich.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.models.FileModel;
import ru.dlyubanevich.user.models.PhotoModel;
import ru.dlyubanevich.user.models.UserSubscriptionModel;

import static ru.dlyubanevich.user.config.RabbitMQConfig.EXCHANGE_NAME;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private static final String ROUTING_KEY_PHOTO_USER = "photo.user";
    private static final String ROUTING_KEY_NOTIFICATION_USER = "notification.user";

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void sendPhotoMessage(String userId, FileModel fileModel){

        if (fileModel == null) {
            return;
        }

        PhotoModel photoModel = new PhotoModel(userId, fileModel);
        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                ROUTING_KEY_PHOTO_USER,
                objectMapper.writeValueAsString(photoModel)
        );

    }

    @SneakyThrows
    @Override
    public void sendNotificationMessage(User user) {
        UserSubscriptionModel userSubscription = new UserSubscriptionModel(user);
        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                ROUTING_KEY_NOTIFICATION_USER,
                objectMapper.writeValueAsString(userSubscription)
        );
    }
}
