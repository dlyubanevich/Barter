package ru.dlyubanevich.offerprocessingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferRequest;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferResponse;
import ru.dlyubanevich.offerprocessingservice.dto.DataRequest;
import ru.dlyubanevich.offerprocessingservice.dto.DataResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final NomenclatureService nomenclatureService;
    private final OfferRequestService offerRequestService;

    @Override
    public OfferRequest processingRequest(DataRequest dataRequest) {
        List<Nomenclature> items = nomenclatureService.addItems(dataRequest.getItems());
        return offerRequestService.addOfferRequest(dataRequest, items);
    }

    @Override
    public OfferResponse processingResponse(DataResponse dataResponse) {
        return null;
    }

}
