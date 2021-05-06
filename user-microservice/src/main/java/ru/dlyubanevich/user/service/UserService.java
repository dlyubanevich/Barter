package ru.dlyubanevich.user.service;

import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;
import ru.dlyubanevich.user.models.UserData;

import java.util.List;

public interface UserService {

    User save(UserData userData);
    List<User> getAll();
    User findById(String id);
    void delete(String id);
    void addUserDetails(String id, UserDetails details);
    void addUserAvatar(String id, String avatar);

}
