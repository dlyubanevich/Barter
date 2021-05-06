package ru.dlyubanevich.nomenclature.service;

import ru.dlyubanevich.nomenclature.domain.UserNomenclature;

import java.util.List;

public interface UserNomenclatureService {

    UserNomenclature save(UserNomenclature userNomenclature);
    void saveAll(List<UserNomenclature> list);
    List<String> getAllNomenclatureIdByUserId(String userId);

}
