package ru.dlyubanevich.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offer.domain.Message;
import ru.dlyubanevich.offer.repository.MessagesRepository;

@Service
@AllArgsConstructor
public class DiscussionMessageServiceImpl implements DiscussionMessageService {

    private final MessagesRepository repository;

    @Transactional
    @Override
    public Message save(Message message) {
        return repository.save(message);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
