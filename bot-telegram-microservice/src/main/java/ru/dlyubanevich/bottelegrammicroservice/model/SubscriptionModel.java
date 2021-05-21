package ru.dlyubanevich.bottelegrammicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionModel {

    private List<NomenclatureOptionModel> nomenclatureOptions;
    private List<String> offerTypes;

}
