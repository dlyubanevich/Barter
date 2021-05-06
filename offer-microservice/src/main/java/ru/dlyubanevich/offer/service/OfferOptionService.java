package ru.dlyubanevich.offer.service;

import ru.dlyubanevich.offer.domain.OfferOption;

import java.util.List;

public interface OfferOptionService {

    OfferOption save(OfferOption offerOption);
    List<OfferOption> getAll();
    void deleteById(String id);

}
