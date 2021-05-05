package ru.dlyubanevich.offers.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offers.domain.*;

import java.util.List;

@Service
@AllArgsConstructor
public class OffersServiceImpl implements OffersService {

    private final OfferRequestService offerRequestService;
    private final OfferResponseService offerResponseService;
    private final MessageService messageService;
    private final OfferDiscussionService offerDiscussionService;
    private final OfferOptionService offerOptionService;

    private static final List<String> OFFER_TYPES = OfferType.getAll();

    @Transactional
    @Override
    public OfferRequest addOfferRequest(OfferRequest offerRequest) {
        OfferDiscussion discussion = new OfferDiscussion();
        offerDiscussionService.save(discussion);
        offerRequest.setDiscussionId(discussion.getId());
        offerRequest.setStatus(Status.NEW);
        return offerRequestService.save(offerRequest);
    }

    @Transactional
    @Override
    public OfferResponse addOfferResponse(OfferResponse offerResponse) {
        OfferDiscussion discussion = new OfferDiscussion();
        offerDiscussionService.save(discussion);
        offerResponse.setDiscussionId(discussion.getId());
        return offerResponseService.save(offerResponse);
    }

    @Transactional
    @Override
    public OfferOption addOfferOption(OfferOption offerOption) {
        return offerOptionService.save(offerOption);
    }

    @Transactional
    @Override
    public Message addMessage(String offerDiscussionId, Message message) {
        Message newMessage = messageService.save(message);
        offerDiscussionService.addMessage(offerDiscussionId, message);
        return newMessage;
    }

    @Transactional
    @Override
    public List<OfferRequest> getAllActiveOfferRequest() {
        return offerRequestService.findAllActiveOffers();
    }

    @Transactional
    @Override
    public List<OfferResponse> getAllOfferResponseByOfferRequestId(String offerRequestId) {
        return offerResponseService.getAllByOfferRequestId(offerRequestId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferOption> getOfferOptions() {
        return offerOptionService.getAll();
    }

    @Transactional
    @Override
    public OfferDiscussion getOfferDiscussionById(String id) {
        return offerDiscussionService.getById(id);
    }

    @Override
    public List<String> getOfferTypes(){
        return OFFER_TYPES;
    }

}
