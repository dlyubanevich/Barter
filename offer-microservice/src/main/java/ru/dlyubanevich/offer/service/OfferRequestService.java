package ru.dlyubanevich.offer.service;

import ru.dlyubanevich.offer.domain.OfferRequest;
import ru.dlyubanevich.offer.domain.User;

import java.util.List;

public interface OfferRequestService {

    OfferRequest save(OfferRequest offerRequest);
    List<OfferRequest> getAllByUser(User user);
    List<OfferRequest> findAllActiveOffers();

}
