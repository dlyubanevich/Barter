package ru.dlyubanevich.offer.model.offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.offer.domain.*;
import ru.dlyubanevich.offer.domain.Requirement;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureModel;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequestModel {

    private Location location;
    private User user;
    private List<NomenclatureModel> items;
    private OfferOption option;
    private String type;
    private String description;
    private List<Requirement> requirements;

}
