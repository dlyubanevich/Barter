package ru.dlyubanevich.offerprocessingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferRequest;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferResponse;
import ru.dlyubanevich.offerprocessingservice.dto.DataRequest;
import ru.dlyubanevich.offerprocessingservice.dto.DataResponse;
import ru.dlyubanevich.offerprocessingservice.service.ProcessingService;

@RestController
@RequiredArgsConstructor
public class OfferProcessingController {

    private final ProcessingService processingService;

    @PostMapping("/api/v1/processing/offer/request")
    public OfferRequest processOfferRequest(@RequestBody DataRequest dataRequest){
        return processingService.processingRequest(dataRequest);
    }

    @PostMapping("/api/v1/processing/offer/response")
    public OfferResponse processOfferResponse(@RequestBody DataResponse dataResponse){
        return processingService.processingResponse(dataResponse);
    }

}
