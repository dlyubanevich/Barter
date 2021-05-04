package ru.dlyubanevich.offers.service;

import ru.dlyubanevich.offers.domain.Message;

public interface MessageService {

    Message save(Message message);
    void deleteById(String id);

}
