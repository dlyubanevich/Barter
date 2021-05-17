package ru.dlyubanevich.nomenclature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.model.NomenclatureModel;
import ru.dlyubanevich.nomenclature.model.PhotoModel;
import ru.dlyubanevich.nomenclature.model.UserNomenclatureModel;

import static ru.dlyubanevich.nomenclature.config.RabbitMQConfig.EXCHANGE_NAME;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private final static String ROUTING_KEY_TO_USER = "user.items";
    private final static String ROUTING_KEY_TO_PHOTO = "photo.nomenclature";

    @SneakyThrows
    @Override
    public void sendUserNomenclatureMessage(Nomenclature nomenclature, NomenclatureModel nomenclatureModel) {
        if (nomenclatureModel.getIsAddableToUserCatalog()){
            UserNomenclatureModel userNomenclatureModel = new UserNomenclatureModel(nomenclature);
            String message = objectMapper.writeValueAsString(userNomenclatureModel);
            sendMessage(ROUTING_KEY_TO_USER, message);
        }
    }

    @SneakyThrows
    @Override
    public void sendPhotoMessage(String itemId, NomenclatureModel nomenclatureModel) {
        var photos = nomenclatureModel.getPhotos();
        if (photos != null && !photos.isEmpty()){
            PhotoModel photoModel = new PhotoModel(itemId, photos);
            String message = objectMapper.writeValueAsString(photoModel);
            sendMessage(ROUTING_KEY_TO_PHOTO, message);
        }
    }

    private void sendMessage(String routingKey, String message){
        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                routingKey,
                message
        );
    }
}
