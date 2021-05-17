package ru.dlyubanevich.nomenclature.messagelistener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.model.NomenclaturePhotoModel;
import ru.dlyubanevich.nomenclature.service.NomenclatureService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ru.dlyubanevich.nomenclature.config.RabbitMQConfig.PHOTO_ACTIVITY_QUEUE;

@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private final ObjectMapper objectMapper;
    private final NomenclatureService nomenclatureService;
    private final Logger logger = Logger.getLogger("RabbitMQListener");

    @SneakyThrows
    @RabbitListener(queues = PHOTO_ACTIVITY_QUEUE)
    public void handleUserActivity(String message) {
        logger.info("RECEIVED from photo-activity-queue: " + message);
        NomenclaturePhotoModel data = objectMapper.readValue(message, NomenclaturePhotoModel.class);
        Nomenclature nomenclature = nomenclatureService.getById(data.getOwnerId());
        List<String> photos = nomenclature.getPhotos();
        if (photos == null){
            photos = new ArrayList<>();
        }
        photos.addAll(data.getItems());
        nomenclature.setPhotos(photos);
        nomenclatureService.save(nomenclature);
    }

}
