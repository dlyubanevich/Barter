package ru.dlyubanevich.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.users.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
