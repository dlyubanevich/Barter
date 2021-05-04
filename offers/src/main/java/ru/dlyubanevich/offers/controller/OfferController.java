package ru.dlyubanevich.offers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dlyubanevich.offers.domain.OfferOption;
import ru.dlyubanevich.offers.domain.OfferRequest;
import ru.dlyubanevich.offers.domain.OfferResponse;
import ru.dlyubanevich.offers.service.OffersService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfferController {

    private final OffersService offersService;

    @PostMapping("/api/v1/offer/request")
    public OfferRequest addOfferRequest(@RequestBody OfferRequest offerRequest){
        return offersService.addOfferRequest(offerRequest);
    }

    @GetMapping("/api/v1/offer/request")
    public List<OfferRequest> getAllActiveOfferRequest(){
        return offersService.getAllActiveOfferRequest();
    }

    @PostMapping("/api/v1/offer/response")
    public OfferResponse addOfferResponse(@RequestBody OfferResponse offerResponse){
        return offersService.addOfferResponse(offerResponse);
    }

    @GetMapping("/api/v1/offer/response/{requestId}")
    public List<OfferResponse> getAllOfferResponseByOfferRequestId(@PathVariable String requestId){
        return offersService.getAllOfferResponseByOfferRequestId(requestId);
    }

    @PostMapping("/api/v1/offer/option")
    public OfferOption addOfferOption(@RequestBody OfferOption offerOption){
        return offersService.addOfferOption(offerOption);
    }

    @GetMapping("/api/v1/offer/option")
    public List<OfferOption> getOfferOptions(){
        return offersService.getOfferOptions();
    }



}
