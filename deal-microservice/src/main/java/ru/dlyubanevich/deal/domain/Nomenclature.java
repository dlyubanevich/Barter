package ru.dlyubanevich.deal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Nomenclature {

    private final String id;
    private final String name;
    private final String type;
    private final NomenclatureOption option;

}
