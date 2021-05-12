package ru.dlyubanevich.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.notification.domain.Subscription;
import ru.dlyubanevich.notification.domain.User;
import ru.dlyubanevich.notification.repository.SubscriptionRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;

    @Transactional
    @Override
    public Subscription save(Subscription subscription) {
        return repository.save(subscription);
    }

    @Transactional(readOnly = true)
    @Override
    public Subscription findByUser(User user) {
        return repository.findFirstByOwnerId(user.getId());
    }

    @Override
    public List<Subscription> getSubscriptions(String userId, List<String> nomenclatureOptionsId, String offerType) {
        return repository.findAllByUsersIdIsOrNomenclatureOptionsIdContainsOrOfferTypesIs(userId, nomenclatureOptionsId, offerType);
    }
}
