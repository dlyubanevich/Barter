package ru.dlyubanevich.nomenclature.service;

import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.domain.User;

import java.util.List;

public interface NomenclatureService {

    Nomenclature save(Nomenclature nomenclature);
    List<Nomenclature> getAllByOwner(User user);
    Nomenclature getById(String id);

}
