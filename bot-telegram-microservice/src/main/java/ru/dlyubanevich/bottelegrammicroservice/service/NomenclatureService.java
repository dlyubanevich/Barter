package ru.dlyubanevich.bottelegrammicroservice.service;

import ru.dlyubanevich.bottelegrammicroservice.model.NomenclatureOptionModel;

import java.util.List;
import java.util.Map;

public interface NomenclatureService {

    Map<String, NomenclatureOptionModel> getNomenclatureOptions();
    List<String> getNomenclatureTypes();

}
