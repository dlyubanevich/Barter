package ru.dlyubanevich.offer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offer.domain.OfferRequest;

import java.util.List;

public interface OfferRequestRepository extends MongoRepository<OfferRequest, String> {

    List<OfferRequest> findAllByUserId(String userId);
    List<OfferRequest> findAllByStatusIn(List<String> statuses);

}
