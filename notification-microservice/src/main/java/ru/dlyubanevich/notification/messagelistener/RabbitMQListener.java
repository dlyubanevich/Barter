package ru.dlyubanevich.notification.messagelistener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.notification.domain.Subscription;
import ru.dlyubanevich.notification.models.OfferRequestModel;
import ru.dlyubanevich.notification.models.OfferResponseModel;
import ru.dlyubanevich.notification.service.SubscriptionService;
import ru.dlyubanevich.notification.service.ProcessingService;

import java.util.logging.Logger;

import static ru.dlyubanevich.notification.config.RabbitMQConfig.*;

@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private final ProcessingService processingService;
    private final SubscriptionService subscriptionService;
    private final ObjectMapper objectMapper;

    private final Logger logger = Logger.getLogger("RabbitMQListener");

    @RabbitListener(queues = USER_SETTINGS_QUEUE)
    public void handleUserSettings(String message) throws JsonProcessingException {
        logger.info("RECEIVED from user-settings-queue: " + message);
        Subscription subscription = objectMapper.readValue(message, Subscription.class);
        Subscription savedSubscription = subscriptionService.findByUser(subscription.getOwner());
        if (savedSubscription == null){
            subscriptionService.save(subscription);
        }else{
            updateSubscription(savedSubscription, subscription);
            subscriptionService.save(savedSubscription);
        }
    }

    private void updateSubscription(Subscription savedSubscription, Subscription subscription) {
        savedSubscription.setNomenclatureOptionsId(subscription.getNomenclatureOptionsId());
        savedSubscription.setOfferTypes(subscription.getOfferTypes());
        savedSubscription.setUsersId(subscription.getUsersId());
    }

    @RabbitListener(queues = OFFER_REQUEST_ACTIVITY_QUEUE)
    public void handleOfferRequestActivity(String message) throws JsonProcessingException{
        logger.info("RECEIVED from offer-request-activity-queue: " + message);
        OfferRequestModel offerRequestModel = objectMapper.readValue(message, OfferRequestModel.class);
        processingService.addOfferRequestNotifications(offerRequestModel);
    }

    @RabbitListener(queues = OFFER_RESPONSE_ACTIVITY_QUEUE)
    public void handleOfferResponseActivity(String message) throws JsonProcessingException{
        logger.info("RECEIVED from offer-response-activity-queue: " + message);
        OfferResponseModel offerResponseModel = objectMapper.readValue(message, OfferResponseModel.class);
        processingService.addOfferResponseNotifications(offerResponseModel);
    }

    @RabbitListener(queues = DEAL_ACTIVITY_QUEUE)
    public void handleDealActivity(String message) throws JsonProcessingException{
        logger.info("RECEIVED from deal-activity-queue: " + message);


    }

}
