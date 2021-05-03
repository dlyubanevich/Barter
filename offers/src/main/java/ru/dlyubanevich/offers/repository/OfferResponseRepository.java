package ru.dlyubanevich.offers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offers.domain.OfferResponse;

public interface OfferResponseRepository extends MongoRepository<OfferResponse, String> {
}
