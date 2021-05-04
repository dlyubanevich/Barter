package ru.dlyubanevich.offers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offers.domain.OfferRequest;

import java.util.List;

public interface OfferRequestRepository extends MongoRepository<OfferRequest, String> {

    List<OfferRequest> findAllByUserId(String userId);
    List<OfferRequest> findAllByStatusIn(List<String> statuses);

}
