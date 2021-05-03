package ru.dlyubanevich.offers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offers.domain.OfferOption;

public interface OfferOptionRepository extends MongoRepository<OfferOption, String> {
}
