package ru.dlyubanevich.offer.service;

import ru.dlyubanevich.offer.domain.OfferResponse;

import java.util.List;

public interface OfferResponseService {
    
    OfferResponse save(OfferResponse offerResponse);
    List<OfferResponse> getAllByOfferRequestId(String offerRequestId);
    
}
