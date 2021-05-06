package ru.dlyubanevich.offer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.offer.domain.Message;

public interface MessagesRepository extends MongoRepository<Message, String> {

}
