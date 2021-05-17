package ru.dlyubanevich.deal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.deal.domain.Location;
import ru.dlyubanevich.deal.domain.OfferRequest;
import ru.dlyubanevich.deal.domain.OfferResponse;
import ru.dlyubanevich.deal.domain.User;

@AllArgsConstructor
@Getter
public class DataDeal {

    private final Location location;
    private final User initiator;
    private final User partner;
    private final OfferRequest offerRequest;
    private final OfferResponse offerResponse;

}
