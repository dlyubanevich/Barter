package ru.dlyubanevich.nomenclature.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NomenclatureItemModel {

    private String id;
    private String name;
    private NomenclatureOption option;
    private String type;

    public NomenclatureItemModel(Nomenclature nomenclature){
        this.id = nomenclature.getId();
        this.name = nomenclature.getName();
        this.option = nomenclature.getOption();
        this.type = nomenclature.getType();
    }
}
