package ru.dlyubanevich.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.user.domain.Nomenclature;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;
import ru.dlyubanevich.user.models.UserDataModel;
import ru.dlyubanevich.user.repository.UserRepository;
import ru.dlyubanevich.user.service.exception.UserNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    @Transactional
    @Override
    public User save(UserDataModel userData) {
        return repository.save(userData.getUser());
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> {throw new UserNotFoundException(id);});
    }

    @Transactional
    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void addUserDetails(String id, UserDetails details) {
        User user = findById(id);
        user.setDetails(details);
        repository.save(user);
    }

    @Transactional
    @Override
    public void addUserAvatar(String id, String avatar) {
        User user = findById(id);
        user.setAvatar(avatar);
        repository.save(user);
    }

    @Transactional
    @Override
    public void addUserNomenclature(String id, List<Nomenclature> items) {
        User user = findById(id);
        items.forEach(item -> user.getItems().add(item));
        repository.save(user);
    }

    @Transactional
    @Override
    public User update(String id, UserDataModel userData) {
        User user = getUserWithUpdatedFields(id, userData);
        return repository.save(user);
    }

    private User getUserWithUpdatedFields(String id, UserDataModel userData) {
        User user = findById(id);
        user.setName(userData.getName());
        user.setPhoneNumber(userData.getPhoneNumber());
        user.setEmail(userData.getEmail());
        user.setGender(userData.getGender());
        user.getSettings().setSubscription(userData.getSubscription());
        return user;
    }
}
