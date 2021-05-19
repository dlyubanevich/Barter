package ru.dlyubanevich.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.notification.domain.*;
import ru.dlyubanevich.notification.models.DealModel;
import ru.dlyubanevich.notification.models.OfferRequestModel;
import ru.dlyubanevich.notification.models.OfferResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private static final String OFFER_REQUEST_DESCRIPTION = "Новое предложение!";
    private static final String OFFER_RESPONSE_DESCRIPTION = "Новый ответ на ваше предложение!";
    private static final String DEAL_DESCRIPTION = "Сделка согласована!";

    private final NotificationService notificationService;
    private final SubscriptionService subscriptionService;

    @Override
    public void addOfferRequestNotifications(OfferRequestModel offerRequestModel) {

        List<String> optionsId = offerRequestModel.getNomenclatureOptions().stream()
                .map(NomenclatureOption::getId)
                .collect(Collectors.toList());
        List<Subscription> subscriptions = subscriptionService.getSubscriptions(
                offerRequestModel.getUser().getId(),
                optionsId,
                offerRequestModel.getType());

        if (subscriptions.size() > 0) {

            List<Notification> notifications = subscriptions.stream()
                .map(subscription -> createOfferRequestNotification(subscription.getOwner(), offerRequestModel.getId()))
                .collect(Collectors.toList());

            notificationService.saveAll(notifications);

        }

    }

    @Override
    public void addOfferResponseNotifications(OfferResponseModel offerResponseModel) {
        Notification notification = createOfferResponseNotification(
                offerResponseModel.getOwnerRequest(),
                offerResponseModel.getId()
        );
        notificationService.save(notification);
    }

    @Transactional
    @Override
    public void addDealNotifications(DealModel dealModel) {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(createDealNotification(dealModel.getInitiator(), dealModel.getId()));
        notifications.add(createDealNotification(dealModel.getPartner(), dealModel.getId()));
        notificationService.saveAll(notifications);
    }

    private Notification createOfferRequestNotification(User recipient, String offerRequestId){
        Identifier identifier = new Identifier();
        identifier.setOfferRequestId(offerRequestId);
        return createNotification(recipient, identifier, OFFER_REQUEST_DESCRIPTION);
    }

    private Notification createOfferResponseNotification(User recipient, String offerResponseId){
        Identifier identifier = new Identifier();
        identifier.setOfferResponseId(offerResponseId);
        return createNotification(recipient, identifier, OFFER_RESPONSE_DESCRIPTION);
    }

    private Notification createDealNotification(User recipient, String dealId){
        Identifier identifier = new Identifier();
        identifier.setDealId(dealId);
        return createNotification(recipient, identifier, DEAL_DESCRIPTION);
    }

    private Notification createNotification(User recipient, Identifier identifier, String description){

        Notification notification = new Notification();
        notification.setDescription(description);
        notification.setIdentifier(identifier);
        notification.setRecipient(recipient);
        notification.setStatus(Status.NEW);

        return notification;

    }
}
