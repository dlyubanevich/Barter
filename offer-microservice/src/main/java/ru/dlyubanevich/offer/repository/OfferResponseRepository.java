package ru.dlyubanevich.offer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offer.domain.OfferResponse;

import java.util.List;

public interface OfferResponseRepository extends MongoRepository<OfferResponse, String> {

    List<OfferResponse> findAllByOfferRequestId(String offerRequestId);

}
