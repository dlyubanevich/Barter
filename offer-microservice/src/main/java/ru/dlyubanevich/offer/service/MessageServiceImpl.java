package ru.dlyubanevich.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offer.domain.Message;
import ru.dlyubanevich.offer.repository.MessagesRepository;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessagesRepository messagesRepository;

    @Transactional
    @Override
    public Message save(Message message) {
        return messagesRepository.save(message);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        messagesRepository.deleteById(id);
    }
}
