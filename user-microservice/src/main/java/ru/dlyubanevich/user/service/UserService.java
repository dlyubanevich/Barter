package ru.dlyubanevich.user.service;

import ru.dlyubanevich.user.domain.Nomenclature;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;
import ru.dlyubanevich.user.models.UserDataModel;

import java.util.List;

public interface UserService {

    User save(UserDataModel userData);
    User update(String id, UserDataModel userData);
    void delete(String id);
    List<User> getAll();
    User findById(String id);
    void addUserDetails(String id, UserDetails details);
    void addUserAvatar(String id, String avatar);
    void addUserNomenclature(String id, List<Nomenclature> items);

}
