package ru.dlyubanevich.offer.service;

import ru.dlyubanevich.offer.domain.Message;

public interface MessageService {

    Message save(Message message);
    void deleteById(String id);

}
