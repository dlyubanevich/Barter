package ru.dlyubanevich.bottelegrammicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequestDataModel {

    private UserModel user;
    private OfferOptionModel option;
    private String type;
    private String description;
    private List<RequirementModel> requirements;
    private List<NomenclatureModel> items;

}
