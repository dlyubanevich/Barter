package ru.dlyubanevich.deal.service;

import ru.dlyubanevich.deal.model.DataDeal;

public interface ProcessingService {

    String addDeal(DataDeal dataDeal);
    DataDeal getDeal(String id);

}
