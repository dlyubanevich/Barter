package ru.dlyubanevich.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.notification.domain.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
