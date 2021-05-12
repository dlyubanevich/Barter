package ru.dlyubanevich.offerprocessingservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferResponse;

@FeignClient("offers")
public interface OfferResponseServiceFeignClient {

    @PostMapping("/api/v1/offer/response")
    OfferResponse addOfferResponse(@RequestBody OfferResponse offerResponse);
}
