package ru.dlyubanevich.offerprocessingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferOption;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferResponse;
import ru.dlyubanevich.offerprocessingservice.domain.other.Location;
import ru.dlyubanevich.offerprocessingservice.domain.other.User;

import java.util.List;

@Data
@AllArgsConstructor
public class DataResponse {

    private User user;
    private String offerRequestId;
    private List<Nomenclature> items;
    private Location location;
    private OfferOption option;
    private String description;

    public OfferResponse transformToOfferResponse(){
        OfferResponse offerResponse = new OfferResponse();
        offerResponse.setDescription(description);
        offerResponse.setItems(getItems());
        offerResponse.setLocation(location);
        offerResponse.setOption(option);
        offerResponse.setUser(user);
        offerResponse.setOfferRequestId(offerRequestId);
        return offerResponse;
    }

    private List<Nomenclature> getItems(){
        items.forEach(nomenclature -> nomenclature.setOwner(user));
        return items;
    }
}
