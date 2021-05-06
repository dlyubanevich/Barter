package ru.dlyubanevich.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Requirement {

    private final NomenclatureOption option;
    private final String description;
}
