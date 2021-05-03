package ru.dlyubanevich.deals.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.deals.domain.DealStatus;

public interface DealStatusRepository extends MongoRepository<DealStatus, String> {
}
