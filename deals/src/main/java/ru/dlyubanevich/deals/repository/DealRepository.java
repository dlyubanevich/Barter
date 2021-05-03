package ru.dlyubanevich.deals.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.deals.domain.Deal;

public interface DealRepository extends MongoRepository<Deal, String> {
}
