package ru.dlyubanevich.nomenclature.service;

import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;
import ru.dlyubanevich.nomenclature.domain.OptionProperty;

import java.util.List;

public interface TotalService {

    Nomenclature addNomenclature(Nomenclature nomenclature, boolean isAddableToUserNomenclature);
    List<Nomenclature> getAllUserNomenclature(String userId);
    NomenclatureOption addNomenclatureOption(NomenclatureOption option);
    OptionProperty addOptionProperty(OptionProperty optionProperty);
    List<NomenclatureOption> getAllNomenclatureOptions();
    List<OptionProperty> getOptionProperties(String optionId);
    List<String> getNomenclatureTypes();


}
