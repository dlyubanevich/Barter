package ru.dlyubanevich.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureOptionModel;

@Getter
@AllArgsConstructor
public class Nomenclature {

    private final String id;
    private final String name;
    private final NomenclatureOptionModel option;
    private final String type;

}
