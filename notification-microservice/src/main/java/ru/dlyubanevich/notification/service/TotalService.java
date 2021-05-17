package ru.dlyubanevich.notification.service;

import ru.dlyubanevich.notification.models.DataDeal;
import ru.dlyubanevich.notification.models.DataOfferRequest;
import ru.dlyubanevich.notification.models.DataOfferResponse;

public interface TotalService {

    void addOfferRequestNotifications(DataOfferRequest dataOfferRequest);
    void addOfferResponseNotifications(DataOfferResponse dataOfferResponse);
    void addDealNotifications(DataDeal dataDeal);
}
