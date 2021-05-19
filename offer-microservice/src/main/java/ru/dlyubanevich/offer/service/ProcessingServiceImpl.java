package ru.dlyubanevich.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offer.domain.*;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureModel;
import ru.dlyubanevich.offer.model.offer.OfferRequestModel;
import ru.dlyubanevich.offer.model.offer.OfferResponseModel;

import java.util.List;

@Service
@AllArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final OfferRequestService offerRequestService;
    private final OfferResponseService offerResponseService;
    private final DiscussionMessageService discussionMessageService;
    private final OfferDiscussionService offerDiscussionService;
    private final OfferOptionService offerOptionService;
    private final NomenclatureService nomenclatureService;
    private final MessageService messageService;

    private final static List<String> OFFER_TYPES = OfferType.getAll();

    @Transactional
    @Override
    public String addOfferRequest(OfferRequestModel offerRequestModel) {

        List<NomenclatureModel> modelItems = offerRequestModel.getItems();
        modelItems.forEach(itemModel -> itemModel.setOwner(offerRequestModel.getUser()));

        List<Nomenclature> items = nomenclatureService.addItems(modelItems);

        String discussionId = addNewDiscussion();
        OfferRequest offerRequest = new OfferRequest(offerRequestModel);
        offerRequest.setDiscussionId(discussionId);
        offerRequest.setItems(items);
        OfferRequest savedOffer = offerRequestService.save(offerRequest);

        messageService.sendOfferRequestNotificationMessage(savedOffer);

        return savedOffer.getId();

    }

    private String addNewDiscussion() {
        OfferDiscussion discussion = new OfferDiscussion();
        return offerDiscussionService.save(discussion).getId();
    }

    @Transactional
    @Override
    public String addOfferResponse(OfferResponseModel offerResponseModel) {

        List<NomenclatureModel> modelItems = offerResponseModel.getItems();
        modelItems.forEach(nomenclature -> nomenclature.setOwner(offerResponseModel.getUser()));

        List<Nomenclature> items = nomenclatureService.addItems(modelItems);

        String discussionId = addNewDiscussion();
        OfferResponse offerResponse = new OfferResponse(offerResponseModel);
        offerResponse.setDiscussionId(discussionId);
        offerResponse.setItems(items);
        OfferResponse savedOffer = offerResponseService.save(offerResponse);

        User ownerRequest = offerRequestService.getOwnerRequest(savedOffer.getOfferRequestId());
        messageService.sendOfferResponseNotificationMessage(ownerRequest, savedOffer);

        return savedOffer.getId();

    }

    @Transactional
    @Override
    public String addOfferOption(OfferOption offerOption) {
        return offerOptionService.save(offerOption).getId();
    }

    @Transactional
    @Override
    public String addMessage(String offerDiscussionId, Message message) {
        Message newMessage = discussionMessageService.save(message);
        offerDiscussionService.addMessage(offerDiscussionId, message);
        return newMessage.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferRequest> getAllActiveOfferRequest() {
        return offerRequestService.findAllActiveOffers();
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferResponse> getAllOfferResponseByOfferRequestId(String offerRequestId) {
        return offerResponseService.getAllByOfferRequestId(offerRequestId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferOption> getOfferOptions() {
        return offerOptionService.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public OfferDiscussion getOfferDiscussionById(String id) {
        return offerDiscussionService.getById(id);
    }

    @Override
    public List<String> getOfferTypes(){
        return OFFER_TYPES;
    }

    @Transactional(readOnly = true)
    @Override
    public OfferRequest getOfferRequestById(String offerRequestId) {
        return offerRequestService.findById(offerRequestId);
    }

    @Transactional(readOnly = true)
    @Override
    public OfferResponse getOfferResponseById(String offerResponseId) {
        return offerResponseService.findById(offerResponseId);
    }
}
