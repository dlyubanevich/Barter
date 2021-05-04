package ru.dlyubanevich.users.service;

import ru.dlyubanevich.users.domain.User;
import ru.dlyubanevich.users.domain.UserDetails;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> getAll();
    User findById(String id);
    void delete(String id);
    void addUserDetails(String id, UserDetails details);

}
