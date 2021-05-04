package ru.dlyubanevich.offers.service;

import ru.dlyubanevich.offers.domain.Message;
import ru.dlyubanevich.offers.domain.OfferDiscussion;

public interface OfferDiscussionService {

    OfferDiscussion save(OfferDiscussion offerDiscussion);
    OfferDiscussion getById(String id);
    void addMessage(String offerDiscussionId, Message message);
}
