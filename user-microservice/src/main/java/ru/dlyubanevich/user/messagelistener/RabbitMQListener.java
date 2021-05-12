package ru.dlyubanevich.user.messagelistener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.user.models.UserAvatar;
import ru.dlyubanevich.user.models.UserNomenclature;
import ru.dlyubanevich.user.service.UserService;

import java.util.logging.Logger;

import static ru.dlyubanevich.user.config.RabbitMQConfig.NOMENCLATURE_ACTIVITY_QUEUE;
import static ru.dlyubanevich.user.config.RabbitMQConfig.PHOTO_ACTIVITY_QUEUE;

@Service
@RequiredArgsConstructor
public class RabbitMQListener{

    private final ObjectMapper objectMapper;
    private final UserService userService;

    private Logger logger = Logger.getLogger("RabbitMQListener");

    @SneakyThrows
    @RabbitListener(queues = PHOTO_ACTIVITY_QUEUE)
    public void setUserAvatar(String message){
        logger.info("RECEIVED from photo-activity-queue: " + message);
        UserAvatar data = objectMapper.readValue(message, UserAvatar.class);
        userService.addUserAvatar(data.getUserId(), data.getUrl());
    }

    @SneakyThrows
    @RabbitListener(queues = NOMENCLATURE_ACTIVITY_QUEUE)
    public void setUserItems(String message){
        logger.info("RECEIVED from nomenclature-activity-queue: " + message);
        UserNomenclature data = objectMapper.readValue(message, UserNomenclature.class);
        userService.addUserNomenclature(data.getId(), data.getItems());
    }
}
