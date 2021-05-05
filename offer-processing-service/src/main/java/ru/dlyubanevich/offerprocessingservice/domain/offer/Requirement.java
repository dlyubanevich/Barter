package ru.dlyubanevich.offerprocessingservice.domain.offer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.NomenclatureOption;

@Getter
@AllArgsConstructor
public class Requirement {

    private final NomenclatureOption option;
    private final String description;
}
