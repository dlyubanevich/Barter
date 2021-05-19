package ru.dlyubanevich.nomenclature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NomenclatureDto {

    private String id;
    private String name;
    private String description;
    private NomenclatureOption option;
    private String type;

    public NomenclatureDto(Nomenclature nomenclature){
        this.id = nomenclature.getId();
        this.name = nomenclature.getName();
        this.description = nomenclature.getDescription();
        this.option = nomenclature.getOption();
        this.type = nomenclature.getType();
    }

}
