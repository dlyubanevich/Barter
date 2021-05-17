package ru.dlyubanevich.offer.service;

import ru.dlyubanevich.offer.domain.OfferRequest;
import ru.dlyubanevich.offer.domain.OfferResponse;
import ru.dlyubanevich.offer.domain.User;

public interface MessageService {

    void sendOfferRequestNotificationMessage(OfferRequest offerRequest);
    void sendOfferResponseNotificationMessage(User ownerRequest, OfferResponse offerResponse);

}
