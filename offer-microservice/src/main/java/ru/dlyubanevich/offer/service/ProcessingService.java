package ru.dlyubanevich.offer.service;

import ru.dlyubanevich.offer.domain.*;
import ru.dlyubanevich.offer.model.offer.OfferRequestModel;
import ru.dlyubanevich.offer.model.offer.OfferResponseModel;

import java.util.List;

public interface ProcessingService {

    String addOfferRequest(OfferRequestModel offerRequestModel);
    String addOfferResponse(OfferResponseModel offerResponseModel);
    String addOfferOption(OfferOption offerOption);
    String addMessage(String offerDiscussionId, Message message);
    List<OfferRequest> getAllActiveOfferRequest();
    List<OfferResponse> getAllOfferResponseByOfferRequestId(String offerRequestId);
    List<OfferOption> getOfferOptions();
    OfferRequest getOfferRequestById(String offerRequestId);
    OfferResponse getOfferResponseById(String offerResponseId);
    OfferDiscussion getOfferDiscussionById(String id);
    List<String> getOfferTypes();

}
