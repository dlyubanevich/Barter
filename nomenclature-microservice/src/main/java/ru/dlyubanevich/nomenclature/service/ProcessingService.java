package ru.dlyubanevich.nomenclature.service;

import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;
import ru.dlyubanevich.nomenclature.domain.OptionProperty;
import ru.dlyubanevich.nomenclature.dto.NomenclatureDto;
import ru.dlyubanevich.nomenclature.model.NomenclatureModel;

import java.util.List;

public interface ProcessingService {

    NomenclatureDto addNomenclature(NomenclatureModel nomenclatureModel);
    Nomenclature getNomenclatureById(String id);
    NomenclatureOption addNomenclatureOption(NomenclatureOption option);
    OptionProperty addOptionProperty(OptionProperty optionProperty);
    List<NomenclatureOption> getAllNomenclatureOptions();
    List<OptionProperty> getOptionProperties(String optionId);
    List<String> getNomenclatureTypes();


}
