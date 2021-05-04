package ru.dlyubanevich.offers.service;

import ru.dlyubanevich.offers.domain.*;

import java.util.List;

public interface OffersService {

    OfferRequest addOfferRequest(OfferRequest offerRequest);
    OfferResponse addOfferResponse(OfferResponse offerResponse);
    OfferOption addOfferOption(OfferOption offerOption);
    Message addMessage(String offerDiscussionId, Message message);
    List<OfferRequest> getAllActiveOfferRequest();
    List<OfferResponse> getAllOfferResponseByOfferRequestId(String offerRequestId);
    List<OfferOption> getOfferOptions();
    OfferDiscussion getOfferDiscussionById(String id);
    List<String> getOfferTypes();

}
