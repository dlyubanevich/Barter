package ru.dlyubanevich.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Nomenclature {

    private final String id;
    private final String name;
    private final NomenclatureOption option;
    private final String type;

}
