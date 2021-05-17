package ru.dlyubanevich.offer.service;

import ru.dlyubanevich.offer.domain.Nomenclature;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureModel;

import java.util.List;

public interface NomenclatureService {

    List<Nomenclature> addItems(List<NomenclatureModel> items);

}
