package ru.dlyubanevich.offerprocessingservice.service;

import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferRequest;
import ru.dlyubanevich.offerprocessingservice.dto.DataRequest;

import java.util.List;

public interface OfferRequestService {

    OfferRequest addOfferRequest(DataRequest dataRequest, List<Nomenclature> items);

}
