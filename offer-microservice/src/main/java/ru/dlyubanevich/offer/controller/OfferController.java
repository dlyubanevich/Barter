package ru.dlyubanevich.offer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dlyubanevich.offer.domain.OfferOption;
import ru.dlyubanevich.offer.domain.OfferRequest;
import ru.dlyubanevich.offer.domain.OfferResponse;
import ru.dlyubanevich.offer.model.offer.OfferRequestModel;
import ru.dlyubanevich.offer.model.offer.OfferResponseModel;
import ru.dlyubanevich.offer.service.ProcessingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfferController {

    private final ProcessingService processingService;

    @PostMapping("/api/v1/offer/request")
    public String addOfferRequest(@RequestBody OfferRequestModel offerRequestModel){
        return processingService.addOfferRequest(offerRequestModel);
    }

    @GetMapping("/api/v1/offer/request")
    public List<OfferRequest> getAllActiveOfferRequest(){
        return processingService.getAllActiveOfferRequest();
    }

    @GetMapping("/api/v1/offer/request/{requestId}")
    public OfferRequest getOfferRequestById(@PathVariable String requestId){
        return processingService.getOfferRequestById(requestId);
    }

    @PostMapping("/api/v1/offer/response")
    public String addOfferResponse(@RequestBody OfferResponseModel offerResponseModel){
        return processingService.addOfferResponse(offerResponseModel);
    }

    @GetMapping("/api/v1/offer/response/request/{requestId}")
    public List<OfferResponse> getAllOfferResponseByOfferRequestId(@PathVariable String requestId){
        return processingService.getAllOfferResponseByOfferRequestId(requestId);
    }

    @GetMapping("/api/v1/offer/response/{responseId}")
    public OfferResponse getOfferResponseById(@PathVariable String responseId){
        return processingService.getOfferResponseById(responseId);
    }

    @PostMapping("/api/v1/offer/option")
    public String addOfferOption(@RequestBody OfferOption offerOption){
        return processingService.addOfferOption(offerOption);
    }

    @GetMapping("/api/v1/offer/option")
    public List<OfferOption> getOfferOptions(){
        return processingService.getOfferOptions();
    }

}
