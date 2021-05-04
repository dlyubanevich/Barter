package ru.dlyubanevich.offers.service;

import ru.dlyubanevich.offers.domain.OfferOption;

import java.util.List;

public interface OfferOptionService {

    OfferOption save(OfferOption offerOption);
    List<OfferOption> getAll();
    void deleteById(String id);

}
