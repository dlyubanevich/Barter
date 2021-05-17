package ru.dlyubanevich.nomenclature.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;
import ru.dlyubanevich.nomenclature.domain.NomenclatureProperty;
import ru.dlyubanevich.nomenclature.domain.User;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NomenclatureModel {

    private String id;
    private String name;
    private User owner;
    private NomenclatureOption option;
    private List<NomenclatureProperty> properties;
    private List<FileModel> photos;
    private String type;
    private String description;
    private Boolean isAddableToUserCatalog;

}
