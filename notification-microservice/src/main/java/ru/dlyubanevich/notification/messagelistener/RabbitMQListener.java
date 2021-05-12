package ru.dlyubanevich.notification.messagelistener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.notification.domain.Subscription;
import ru.dlyubanevich.notification.models.OfferModel;
import ru.dlyubanevich.notification.service.SubscriptionService;
import ru.dlyubanevich.notification.service.TotalService;

import java.util.logging.Logger;

import static ru.dlyubanevich.notification.config.RabbitMQConfig.OFFER_ACTIVITY_QUEUE;
import static ru.dlyubanevich.notification.config.RabbitMQConfig.USER_SETTINGS_QUEUE;

@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TotalService totalService;
    private final SubscriptionService subscriptionService;
    private final ObjectMapper objectMapper;

    private Logger logger = Logger.getLogger("RabbitMQListener");

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

    @RabbitListener(queues = OFFER_ACTIVITY_QUEUE)
    public void handleOfferActivity(String message) throws JsonProcessingException{
        logger.info("RECEIVED from offer-activity-queue: " + message);
        OfferModel offerModel = objectMapper.readValue(message, OfferModel.class);
        totalService.addNotifications(offerModel.getOffer());
    }

}
