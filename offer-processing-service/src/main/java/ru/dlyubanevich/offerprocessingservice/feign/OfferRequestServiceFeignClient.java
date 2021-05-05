package ru.dlyubanevich.offerprocessingservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferRequest;

@FeignClient("offers")
public interface OfferRequestServiceFeignClient {

    @PostMapping("/api/v1/offer/request")
    OfferRequest addOfferRequest(@RequestBody OfferRequest offerRequest);

}
