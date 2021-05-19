package ru.dlyubanevich.notification.service;

import ru.dlyubanevich.notification.models.DealModel;
import ru.dlyubanevich.notification.models.OfferRequestModel;
import ru.dlyubanevich.notification.models.OfferResponseModel;

public interface ProcessingService {

    void addOfferRequestNotifications(OfferRequestModel offerRequestModel);
    void addOfferResponseNotifications(OfferResponseModel offerResponseModel);
    void addDealNotifications(DealModel dealModel);
}
