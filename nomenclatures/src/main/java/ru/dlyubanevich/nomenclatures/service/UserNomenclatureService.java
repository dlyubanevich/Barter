package ru.dlyubanevich.nomenclatures.service;

import ru.dlyubanevich.nomenclatures.domain.UserNomenclature;

import java.util.List;

public interface UserNomenclatureService {

    UserNomenclature save(UserNomenclature userNomenclature);
    void saveAll(List<UserNomenclature> list);
    List<String> getAllNomenclatureIdByUserId(String userId);

}
