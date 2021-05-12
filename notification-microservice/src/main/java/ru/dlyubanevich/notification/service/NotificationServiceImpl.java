package ru.dlyubanevich.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.notification.domain.Notification;
import ru.dlyubanevich.notification.repository.NotificationRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    @Transactional
    @Override
    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    @Transactional
    @Override
    public void saveAll(List<Notification> notificationList) {
        repository.saveAll(notificationList);
    }
}
