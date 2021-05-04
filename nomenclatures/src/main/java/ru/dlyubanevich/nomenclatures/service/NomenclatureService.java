package ru.dlyubanevich.nomenclatures.service;

import ru.dlyubanevich.nomenclatures.domain.Nomenclature;
import ru.dlyubanevich.nomenclatures.domain.User;

import java.util.List;

public interface NomenclatureService {

    Nomenclature save(Nomenclature nomenclature);
    List<Nomenclature> getAllByOwner(User user);
    List<Nomenclature> getAll(List<String> ids);

}
