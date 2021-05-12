package ru.dlyubanevich.offerprocessingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferRequest;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferResponse;
import ru.dlyubanevich.offerprocessingservice.feign.OfferRequestServiceFeignClient;
import ru.dlyubanevich.offerprocessingservice.feign.OfferResponseServiceFeignClient;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRequestServiceFeignClient offerRequestServiceFeignClient;
    private final OfferResponseServiceFeignClient offerResponseServiceFeignClient;

    @Override
    public OfferRequest addOfferRequest(OfferRequest offerRequest) {
        var createdOffer = offerRequestServiceFeignClient.addOfferRequest(offerRequest);
        createdOffer.setItems(offerRequest.getItems());
        return createdOffer;
    }

    @Override
    public OfferResponse addOfferResponse(OfferResponse offerResponse) {
        var createdOffer = offerResponseServiceFeignClient.addOfferResponse(offerResponse);
        createdOffer.setItems(offerResponse.getItems());
        return createdOffer;
    }

}
