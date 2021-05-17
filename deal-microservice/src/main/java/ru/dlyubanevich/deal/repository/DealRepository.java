package ru.dlyubanevich.deal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.deal.domain.Deal;
import ru.dlyubanevich.deal.domain.User;

public interface DealRepository extends MongoRepository<Deal, String> {

    int countDealsByInitiatorId(String initiatorId);

}
