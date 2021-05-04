package ru.dlyubanevich.offers.service;

import ru.dlyubanevich.offers.domain.OfferResponse;

import java.util.List;

public interface OfferResponseService {
    
    OfferResponse save(OfferResponse offerResponse);
    List<OfferResponse> getAllByOfferRequestId(String offerRequestId);
    
}
