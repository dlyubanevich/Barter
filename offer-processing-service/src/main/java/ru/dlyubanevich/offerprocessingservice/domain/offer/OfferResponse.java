package ru.dlyubanevich.offerprocessingservice.domain.offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.offerprocessingservice.domain.other.Location;
import ru.dlyubanevich.offerprocessingservice.domain.other.User;
import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponse {

    private String id;
    private User user;
    private String offerRequestId;
    private List<Nomenclature> items;
    private Location location;
    private OfferOption option;
    private String description;
    private String discussionId;

}
