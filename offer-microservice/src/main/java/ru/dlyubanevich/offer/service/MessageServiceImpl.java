package ru.dlyubanevich.offer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.offer.domain.OfferRequest;
import ru.dlyubanevich.offer.domain.OfferResponse;
import ru.dlyubanevich.offer.domain.User;
import ru.dlyubanevich.offer.model.notification.OfferRequestNotificationModel;
import ru.dlyubanevich.offer.model.notification.OfferResponseNotificationModel;

import static ru.dlyubanevich.offer.config.RabbitMQConfig.EXCHANGE_NAME;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private final static String ROUTING_KEY_TO_NOTIFICATION_REQUEST = "notification.offer.request";
    private final static String ROUTING_KEY_TO_NOTIFICATION_RESPONSE = "notification.offer.response";

    @SneakyThrows
    @Override
    public void sendOfferRequestNotificationMessage(OfferRequest offerRequest) {
        OfferRequestNotificationModel offerRequestNotification = new OfferRequestNotificationModel(offerRequest);
        String message = objectMapper.writeValueAsString(offerRequestNotification);
        sendMessage(ROUTING_KEY_TO_NOTIFICATION_REQUEST, message);
    }

    @SneakyThrows
    @Override
    public void sendOfferResponseNotificationMessage(User ownerRequest, OfferResponse offerResponse) {
        OfferResponseNotificationModel offerResponseNotification = new OfferResponseNotificationModel(offerResponse, ownerRequest);
        String message = objectMapper.writeValueAsString(offerResponseNotification);
        sendMessage(ROUTING_KEY_TO_NOTIFICATION_RESPONSE, message);
    }

    private void sendMessage(String routingKey, String message){
        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                routingKey,
                message
        );
    }
}
