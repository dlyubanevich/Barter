package ru.dlyubanevich.nomenclature.service;

import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;

import java.util.List;

public interface NomenclatureOptionService {

    NomenclatureOption save(NomenclatureOption option);
    List<NomenclatureOption> getAll();

}
