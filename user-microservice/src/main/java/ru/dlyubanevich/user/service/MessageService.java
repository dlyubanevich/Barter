package ru.dlyubanevich.user.service;

import ru.dlyubanevich.user.domain.User;

public interface MessageService {

    void sendPhotoMessage(String userId, String photo);
    void sendNotificationMessage(User user);

}
