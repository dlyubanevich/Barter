package ru.dlyubanevich.notification.service;

import ru.dlyubanevich.notification.domain.Subscription;
import ru.dlyubanevich.notification.domain.User;

import java.util.List;

public interface SubscriptionService {

    Subscription save(Subscription subscription);
    Subscription findByUser(User user);
    List<Subscription> getSubscriptions(String userId, List<String> optionsId, String offerType);

}
