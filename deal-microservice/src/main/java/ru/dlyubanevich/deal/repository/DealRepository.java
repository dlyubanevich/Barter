package ru.dlyubanevich.deal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.deal.domain.Deal;

public interface DealRepository extends MongoRepository<Deal, String> {
}
