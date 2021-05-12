package ru.dlyubanevich.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.notification.domain.Subscription;

import java.util.List;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

    Subscription findFirstByOwnerId(String id);
    List<Subscription> findAllByUsersIdIsOrNomenclatureOptionsIdContainsOrOfferTypesIs(String userId, List<String> nomenclatureOptionsId, String offerType);

}
