package ru.dlyubanevich.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.user.domain.Nomenclature;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserNomenclature {

    private final String id;
    private final List<Nomenclature> items;

}
