package ru.dlyubanevich.notification.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.notification.domain.NomenclatureOption;
import ru.dlyubanevich.notification.domain.OfferRequest;
import ru.dlyubanevich.notification.domain.User;

import java.util.List;

@AllArgsConstructor
@Getter
public class DataOfferRequest {

    private final String id;
    private final String description;
    private final String type;
    private final List<NomenclatureOption> nomenclatureOptions;
    private final User user;

}
