package ru.dlyubanevich.bottelegrammicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequirementModel {

    private NomenclatureOptionModel option;
    private String description;

}
