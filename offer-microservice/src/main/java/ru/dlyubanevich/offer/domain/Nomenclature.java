package ru.dlyubanevich.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureOptionModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nomenclature {

    private String id;
    private String name;
    private String description;
    private NomenclatureOptionModel option;
    private String type;

}
