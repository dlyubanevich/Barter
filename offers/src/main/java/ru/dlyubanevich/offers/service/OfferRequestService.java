package ru.dlyubanevich.offers.service;

import ru.dlyubanevich.offers.domain.OfferRequest;
import ru.dlyubanevich.offers.domain.User;

import java.util.List;

public interface OfferRequestService {

    OfferRequest save(OfferRequest offerRequest);
    List<OfferRequest> getAllByUser(User user);
    List<OfferRequest> findAllActiveOffers();

}
