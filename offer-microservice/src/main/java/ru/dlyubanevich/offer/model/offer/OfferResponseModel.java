package ru.dlyubanevich.offer.model.offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.offer.domain.Location;
import ru.dlyubanevich.offer.domain.OfferOption;
import ru.dlyubanevich.offer.domain.User;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureModel;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponseModel {

    private User user;
    private String offerRequestId;
    private List<NomenclatureModel> items;
    private Location location;
    private OfferOption option;
    private String description;

}
