package ru.dlyubanevich.photo.messagelistener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.photo.model.FileModel;
import ru.dlyubanevich.photo.model.UploadedPhotoModel;
import ru.dlyubanevich.photo.model.PhotoModel;
import ru.dlyubanevich.photo.service.PhotoService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static ru.dlyubanevich.photo.config.RabbitMQConfig.*;

@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private static final String ROUTING_KEY_TO_USER = "user.avatar";
    private static final String ROUTING_KEY_TO_NOMENCLATURE = "nomenclature.photo";

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final PhotoService photoService;

    private final Logger logger = Logger.getLogger("RabbitMQListener");

    @RabbitListener(queues = USER_ACTIVITY_QUEUE)
    public void handleUserActivity(String message) {
        logger.info("RECEIVED from user-activity-queue");
        String replyMessage = uploadFilesAndPrepareReplyMessage(message);
        sendMessage(ROUTING_KEY_TO_USER, replyMessage);
    }

    @RabbitListener(queues = NOMENCLATURE_ACTIVITY_QUEUE)
    public void handleNomenclatureActivity(String message){
        logger.info("RECEIVED from nomenclature-activity-queue");
        String replyMessage = uploadFilesAndPrepareReplyMessage(message);
        sendMessage(ROUTING_KEY_TO_NOMENCLATURE, replyMessage);
    }

    @SneakyThrows
    private String uploadFilesAndPrepareReplyMessage(String message) {
        PhotoModel photoModel = objectMapper.readValue(message, PhotoModel.class);
        List<String> items = getUploadedFiles(photoModel.getFiles());
        UploadedPhotoModel uploadedPhotoModel = new UploadedPhotoModel(photoModel.getOwnerId(), items);
        return objectMapper.writeValueAsString(uploadedPhotoModel);
    }

    private List<String> getUploadedFiles(List<FileModel> files) {
        return files.stream()
                .map(photoService::uploadFile)
                .collect(Collectors.toList());
    }

    private void sendMessage(String routingKey, String message){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, message);
    }

}
