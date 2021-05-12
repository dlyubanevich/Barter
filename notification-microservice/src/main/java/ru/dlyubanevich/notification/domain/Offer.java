package ru.dlyubanevich.notification.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Offer {

    private final String id;
    private final String description;
    private final String type;
    private final List<NomenclatureOption> nomenclatureOption;
    private final User owner;

}
