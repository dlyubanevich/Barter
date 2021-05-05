package ru.dlyubanevich.offerprocessingservice.domain.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dlyubanevich.offerprocessingservice.domain.other.User;

import java.util.List;

@Data
@AllArgsConstructor
public class Nomenclature {

    private String id;
    private String name;
    private User owner;
    private NomenclatureOption option;
    private List<NomenclatureProperty> properties;
    private List<String> photos;
    private String type;
    private String description;
    private boolean isAddableToUserNomenclature;

}
