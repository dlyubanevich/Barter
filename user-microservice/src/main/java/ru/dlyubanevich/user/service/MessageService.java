package ru.dlyubanevich.user.service;

import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.models.FileModel;

public interface MessageService {

    void sendPhotoMessage(String userId, FileModel fileModel);
    void sendNotificationMessage(User user);

}
