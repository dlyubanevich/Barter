package ru.dlyubanevich.offerprocessingservice.service;

import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;

import java.util.List;

public interface NomenclatureService {

    List<Nomenclature> addItems(List<Nomenclature> items);

}
