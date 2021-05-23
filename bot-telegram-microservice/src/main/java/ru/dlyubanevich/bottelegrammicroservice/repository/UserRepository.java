package ru.dlyubanevich.bottelegrammicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.bottelegrammicroservice.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByTelegramId(Long telegramId);
    boolean existsUserByTelegramId(Long telegramId);

}
