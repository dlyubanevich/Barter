package ru.dlyubanevich.offerprocessingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferRequest;
import ru.dlyubanevich.offerprocessingservice.dto.DataRequest;
import ru.dlyubanevich.offerprocessingservice.feign.OfferRequestServiceFeignClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferRequestServiceImpl implements OfferRequestService {

    private final OfferRequestServiceFeignClient offerRequestServiceFeignClient;

    @Override
    public OfferRequest addOfferRequest(DataRequest dataRequest, List<Nomenclature> items) {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.setDescription(dataRequest.getDescription());
        offerRequest.setItems(items);
        offerRequest.setLocation(dataRequest.getLocation());
        offerRequest.setOption(dataRequest.getOption());
        offerRequest.setUser(dataRequest.getUser());
        offerRequest.setType(dataRequest.getType());
        offerRequest.setRequirements(dataRequest.getRequirements());
        var createdOffer = offerRequestServiceFeignClient.addOfferRequest(offerRequest);
        createdOffer.setItems(items);
        return createdOffer;
    }
}
