package ru.dlyubanevich.offers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offers.domain.OfferRequest;

public interface OfferRequestRepository extends MongoRepository<OfferRequest, String> {
}
