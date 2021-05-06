package ru.dlyubanevich.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.user.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
