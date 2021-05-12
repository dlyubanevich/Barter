package ru.dlyubanevich.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.notification.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TotalServiceImpl implements TotalService {

    private final NotificationService notificationService;
    private final SubscriptionService subscriptionService;

    @Override
    public void addNotifications(Offer offer) {

        List<String> optionsId = offer.getNomenclatureOption().stream()
                .map(NomenclatureOption::getId)
                .collect(Collectors.toList());
        List<Subscription> subscriptions = subscriptionService.getSubscriptions(
                offer.getOwner().getId(),
                optionsId,
                offer.getType());

        if (subscriptions.size() > 0) {

            List<Notification> notifications = subscriptions.stream()
                .map(subscription -> createNotification(subscription.getOwner(), offer))
                .collect(Collectors.toList());

            notificationService.saveAll(notifications);

        }

    }

    private Notification createNotification(User recipient, Offer offer){
        Notification notification = new Notification();
        notification.setOffer(offer);
        notification.setRecipient(recipient);
        notification.setStatus(Status.NEW);
        return notification;
    }
}
