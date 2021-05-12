package ru.dlyubanevich.offerprocessingservice.service;

import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferRequest;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferResponse;

public interface OfferService {

    OfferRequest addOfferRequest(OfferRequest offerRequest);
    OfferResponse addOfferResponse(OfferResponse offerResponse);

}
