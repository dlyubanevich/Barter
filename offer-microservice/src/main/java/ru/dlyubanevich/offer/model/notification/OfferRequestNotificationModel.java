package ru.dlyubanevich.offer.model.notification;

import lombok.Getter;
import ru.dlyubanevich.offer.domain.Nomenclature;
import ru.dlyubanevich.offer.domain.OfferRequest;
import ru.dlyubanevich.offer.domain.User;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureOptionModel;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OfferRequestNotificationModel {

    private final String id;
    private final String description;
    private final String type;
    private final List<NomenclatureOptionModel> nomenclatureOptions;
    private final User user;

    public OfferRequestNotificationModel(OfferRequest offerRequest){
        this.id = offerRequest.getId();
        this.description = offerRequest.getDescription();
        this.type = offerRequest.getType();
        this.user = offerRequest.getUser();
        this.nomenclatureOptions = offerRequest.getItems().stream()
                .map(Nomenclature::getOption)
                .distinct()
                .collect(Collectors.toList());
    }

}
