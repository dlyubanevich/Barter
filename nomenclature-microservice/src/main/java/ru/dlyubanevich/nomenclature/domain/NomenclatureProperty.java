package ru.dlyubanevich.nomenclature.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NomenclatureProperty {

    private String name;
    private String value;

}
