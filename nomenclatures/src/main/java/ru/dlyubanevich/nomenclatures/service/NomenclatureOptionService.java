package ru.dlyubanevich.nomenclatures.service;

import ru.dlyubanevich.nomenclatures.domain.NomenclatureOption;

import java.util.List;

public interface NomenclatureOptionService {

    NomenclatureOption save(NomenclatureOption option);
    List<NomenclatureOption> getAll();

}
