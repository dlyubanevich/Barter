package ru.dlyubanevich.offer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offer.domain.OfferDiscussion;

import java.util.Optional;

public interface OfferDiscussionRepository extends MongoRepository<OfferDiscussion, String> {

    Optional<OfferDiscussion> findById(String id);

}
