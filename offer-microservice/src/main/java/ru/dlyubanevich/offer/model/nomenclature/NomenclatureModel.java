package ru.dlyubanevich.offer.model.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.offer.domain.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NomenclatureModel {

    private String id;
    private String name;
    private User owner;
    private NomenclatureOptionModel option;
    private List<NomenclaturePropertyModel> properties;
    private List<FileModel> photos;
    private String type;
    private String description;
    private Boolean isAddableToUserCatalog;

}
