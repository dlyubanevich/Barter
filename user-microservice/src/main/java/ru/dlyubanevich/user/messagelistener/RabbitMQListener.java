package ru.dlyubanevich.user.messagelistener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.user.models.UserAvatarModel;
import ru.dlyubanevich.user.models.UserNomenclatureModel;
import ru.dlyubanevich.user.service.UserService;

import java.util.logging.Logger;

import static ru.dlyubanevich.user.config.RabbitMQConfig.NOMENCLATURE_ACTIVITY_QUEUE;
import static ru.dlyubanevich.user.config.RabbitMQConfig.PHOTO_ACTIVITY_QUEUE;

@Service
@RequiredArgsConstructor
public class RabbitMQListener{

    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final Logger logger = Logger.getLogger("RabbitMQListener");

    @RabbitListener(queues = PHOTO_ACTIVITY_QUEUE)
    public void setUserAvatar(String message) throws JsonProcessingException{
        logger.info("RECEIVED from photo-activity-queue: " + message);
        UserAvatarModel data = objectMapper.readValue(message, UserAvatarModel.class);
        if (data.getItems() != null && data.getItems().size() > 0) {
            userService.addUserAvatar(data.getOwnerId(), data.getItems().get(0));
        }
    }

    @RabbitListener(queues = NOMENCLATURE_ACTIVITY_QUEUE)
    public void setUserItems(String message) throws JsonProcessingException{
        logger.info("RECEIVED from nomenclature-activity-queue: " + message);
        UserNomenclatureModel data = objectMapper.readValue(message, UserNomenclatureModel.class);
        userService.addUserNomenclature(data.getUserId(), data.getItems());
    }
}
