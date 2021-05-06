package ru.dlyubanevich.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offer.domain.Message;
import ru.dlyubanevich.offer.domain.OfferDiscussion;
import ru.dlyubanevich.offer.repository.OfferDiscussionRepository;

@Service
@AllArgsConstructor
public class OfferDiscussionServiceImpl implements OfferDiscussionService {

    private final OfferDiscussionRepository offerDiscussionRepository;

    @Transactional
    @Override
    public OfferDiscussion save(OfferDiscussion offerDiscussion) {
        return offerDiscussionRepository.save(offerDiscussion);
    }

    @Transactional(readOnly = true)
    @Override
    public OfferDiscussion getById(String id) {
        return offerDiscussionRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public void addMessage(String offerDiscussionId, Message message) {
        OfferDiscussion offerDiscussion = offerDiscussionRepository.findById(offerDiscussionId).orElseThrow();
        offerDiscussion.addMessage(message);
        offerDiscussionRepository.save(offerDiscussion);
    }
}
