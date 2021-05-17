package ru.dlyubanevich.deal.service;

import ru.dlyubanevich.deal.domain.Deal;
import ru.dlyubanevich.deal.domain.User;

public interface DealService {

    Deal addDeal(Deal deal);
    int getNextDealNumber(User initiator);
    Deal getDeal(String id);

}
