package ru.dlyubanevich.photo.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.photo.models.UserAvatar;
import ru.dlyubanevich.photo.models.UserPhoto;

import java.util.logging.Logger;

import static ru.dlyubanevich.photo.config.RabbitMQConfig.EXCHANGE_NAME;
import static ru.dlyubanevich.photo.config.RabbitMQConfig.USER_ACTIVITY_QUEUE;

@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private Logger logger = Logger.getLogger("RabbitMQListener");

    @RabbitListener(queues = USER_ACTIVITY_QUEUE)
    public void handleUserActivity(String message) throws JsonProcessingException {
        logger.info("RECEIVED from user-activity-queue: " + message);
        UserPhoto data = objectMapper.readValue(message, UserPhoto.class);
        UserAvatar userAvatar = new UserAvatar(data.getUserId(), "https://disk.yandex.ru/i/EF3-MuC91x79BB");
        String routingKey = "user.avatar";
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, objectMapper.writeValueAsString(userAvatar));
    }
}
