package ru.dlyubanevich.offerprocessingservice.service;

import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferRequest;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferResponse;
import ru.dlyubanevich.offerprocessingservice.dto.DataRequest;
import ru.dlyubanevich.offerprocessingservice.dto.DataResponse;

public interface ProcessingService {

    OfferRequest processingRequest(DataRequest dataRequest);
    OfferResponse processingResponse(DataResponse dataResponse);

}
