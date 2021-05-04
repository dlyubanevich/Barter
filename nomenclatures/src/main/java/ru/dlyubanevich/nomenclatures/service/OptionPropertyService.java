package ru.dlyubanevich.nomenclatures.service;

import ru.dlyubanevich.nomenclatures.domain.OptionProperty;

import java.util.List;

public interface OptionPropertyService {

    OptionProperty save(OptionProperty optionProperty);
    List<OptionProperty> getAllByOptionId(String optionId);

}
