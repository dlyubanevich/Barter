package ru.dlyubanevich.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureOptionModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Requirement {

    private NomenclatureOptionModel option;
    private String description;

}
