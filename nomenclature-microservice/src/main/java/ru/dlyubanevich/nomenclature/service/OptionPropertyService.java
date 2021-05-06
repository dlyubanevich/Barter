package ru.dlyubanevich.nomenclature.service;

import ru.dlyubanevich.nomenclature.domain.OptionProperty;

import java.util.List;

public interface OptionPropertyService {

    OptionProperty save(OptionProperty optionProperty);
    List<OptionProperty> getAllByOptionId(String optionId);

}
