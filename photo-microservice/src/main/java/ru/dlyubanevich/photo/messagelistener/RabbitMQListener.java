package ru.dlyubanevich.photo.messagelistener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.photo.models.NomenclaturePhoto;
import ru.dlyubanevich.photo.models.UserAvatar;
import ru.dlyubanevich.photo.models.UserPhoto;
import ru.dlyubanevich.photo.service.PhotoService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ru.dlyubanevich.photo.config.RabbitMQConfig.*;

@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private final static String ROUTING_KEY_TO_USER = "user.avatar";
    private final static String ROUTING_KEY_TO_NOMENCLATURE = "nomenclature.photo";

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final PhotoService photoService;

    private Logger logger = Logger.getLogger("RabbitMQListener");

    @SneakyThrows
    @RabbitListener(queues = USER_ACTIVITY_QUEUE)
    public void handleUserActivity(String message) {
        logger.info("RECEIVED from user-activity-queue: " + message);
        UserPhoto data = objectMapper.readValue(message, UserPhoto.class);
        String URL = getPhotoUrl(data.getPhoto());
        UserAvatar userAvatar = new UserAvatar(data.getUserId(), URL);
        sendMessage(ROUTING_KEY_TO_USER, objectMapper.writeValueAsString(userAvatar));
    }

    @SneakyThrows
    @RabbitListener(queues = NOMENCLATURE_ACTIVITY_QUEUE)
    public void handleNomenclatureActivity(String message){
        logger.info("RECEIVED from nomenclature-activity-queue: " + message);
        NomenclaturePhoto data = objectMapper.readValue(message, NomenclaturePhoto.class);
        List<String> photos = getConvertedPhotos(data.getPhotos());
        NomenclaturePhoto nomenclaturePhoto = new NomenclaturePhoto(data.getId(), photos);
        sendMessage(ROUTING_KEY_TO_NOMENCLATURE, objectMapper.writeValueAsString(nomenclaturePhoto));
    }

    private void sendMessage(String routingKey, String message){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, message);
    }

    private String getPhotoUrl(String photo){
        byte[] bytes = photoService.convert(photo);
        return photoService.load(bytes);
    }

    private List<String> getConvertedPhotos(List<String> photos) {
        List<String> convertedPhotos = new ArrayList<>();
        photos.forEach(photo -> convertedPhotos.add(getPhotoUrl(photo)));
        return convertedPhotos;
    }

}
