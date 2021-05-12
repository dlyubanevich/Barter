package ru.dlyubanevich.deal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.deal.domain.DealStatus;

public interface DealStatusRepository extends MongoRepository<DealStatus, String> {
}