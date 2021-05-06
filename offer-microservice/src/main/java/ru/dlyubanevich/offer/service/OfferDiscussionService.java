package ru.dlyubanevich.offer.service;

import ru.dlyubanevich.offer.domain.Message;
import ru.dlyubanevich.offer.domain.OfferDiscussion;

public interface OfferDiscussionService {

    OfferDiscussion save(OfferDiscussion offerDiscussion);
    OfferDiscussion getById(String id);
    void addMessage(String offerDiscussionId, Message message);
}
