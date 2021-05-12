package ru.dlyubanevich.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Nomenclature {

    private final String id;
    private final String name;
    private final NomenclatureOption option;
    private final String type;

}
