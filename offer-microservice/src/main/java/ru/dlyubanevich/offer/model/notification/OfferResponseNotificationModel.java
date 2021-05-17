package ru.dlyubanevich.offer.model.notification;

import lombok.Getter;
import ru.dlyubanevich.offer.domain.OfferResponse;
import ru.dlyubanevich.offer.domain.User;

@Getter
public class OfferResponseNotificationModel {

    private final String id;
    private final String description;
    private final User user;
    private final User ownerRequest;
    private final String offerRequestId;

    public OfferResponseNotificationModel(OfferResponse offerResponse, User ownerRequest){
        this.id = offerResponse.getId();
        this.description = offerResponse.getDescription();
        this.user = offerResponse.getUser();
        this.offerRequestId = offerResponse.getOfferRequestId();
        this.ownerRequest = ownerRequest;
    }

}
