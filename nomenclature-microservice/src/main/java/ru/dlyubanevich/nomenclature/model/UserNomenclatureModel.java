package ru.dlyubanevich.nomenclature.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.nomenclature.domain.Nomenclature;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserNomenclatureModel {

    private String userId;
    private List<NomenclatureItemModel> items;

    public UserNomenclatureModel(Nomenclature nomenclature){
        this.userId = nomenclature.getOwner().getId();
        this.items = Collections.singletonList(new NomenclatureItemModel(nomenclature));
    }
}
