package ru.dlyubanevich.offer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offer.domain.OfferOption;

public interface OfferOptionRepository extends MongoRepository<OfferOption, String> {

}
