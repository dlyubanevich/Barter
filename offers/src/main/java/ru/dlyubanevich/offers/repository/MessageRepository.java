package ru.dlyubanevich.offers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offers.domain.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
}
