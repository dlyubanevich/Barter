package ru.dlyubanevich.offers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offers.domain.Message;

public interface MessagesRepository extends MongoRepository<Message, String> {

}
