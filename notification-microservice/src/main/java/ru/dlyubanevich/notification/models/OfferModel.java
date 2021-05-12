package ru.dlyubanevich.notification.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.notification.domain.NomenclatureOption;
import ru.dlyubanevich.notification.domain.Offer;
import ru.dlyubanevich.notification.domain.User;

import java.util.List;

@AllArgsConstructor
@Getter
public class OfferModel {

    private final String id;
    private final String description;
    private final String type;
    private final List<NomenclatureOption> optionList;
    private final User user;

    public Offer getOffer(){
        return new Offer(id, description, type, optionList, user);
    }
}
