package ru.dlyubanevich.offerprocessingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferOption;
import ru.dlyubanevich.offerprocessingservice.domain.offer.Requirement;
import ru.dlyubanevich.offerprocessingservice.domain.other.Location;
import ru.dlyubanevich.offerprocessingservice.domain.other.User;

import java.util.List;

@Data
@AllArgsConstructor
public class DataRequest {

    private Location location;
    private User user;
    private List<Nomenclature> items;
    private OfferOption option;
    private String type;
    private String description;
    private List<Requirement> requirements;

}
