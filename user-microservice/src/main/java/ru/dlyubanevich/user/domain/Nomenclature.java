package ru.dlyubanevich.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Nomenclature {

    private String id;
    private String name;
    private NomenclatureOption option;
    private String type;

}
