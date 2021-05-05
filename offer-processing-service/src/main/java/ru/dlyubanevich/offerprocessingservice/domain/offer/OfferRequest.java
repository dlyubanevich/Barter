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
public class OfferRequest {

    private String id;
    private User user;
    private List<Nomenclature> items;
    private Location location;
    private String description;
    private String type;
    private OfferOption option;
    private List<Requirement> requirements;
    private String status;
    private String discussionId;

}
