package ru.dlyubanevich.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.user.domain.Nomenclature;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserNomenclatureModel {

    private String userId;
    private List<Nomenclature> items;

}
