package ru.dlyubanevich.notification.service;

import ru.dlyubanevich.notification.domain.Notification;

import java.util.List;

public interface NotificationService {

    Notification save(Notification notification);
    void saveAll(List<Notification> notificationList);

}
