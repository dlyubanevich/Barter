package ru.dlyubanevich.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.user.domain.Nomenclature;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;
import ru.dlyubanevich.user.models.UserData;
import ru.dlyubanevich.user.repository.UserRepository;
import ru.dlyubanevich.user.service.exception.UserNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final MessageService messageService;

    @Transactional
    @Override
    public User save(UserData userData) {
        User user = userRepository.save(userData.getUser());
        sendMessagesIfNecessary(user, userData);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {throw new UserNotFoundException("There is no User by id " + id);});
    }

    @Transactional
    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void addUserDetails(String id, UserDetails details) {
        User user = findById(id);
        user.setDetails(details);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void addUserAvatar(String id, String avatar) {
        User user = findById(id);
        user.setAvatar(avatar);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void addUserNomenclature(String id, List<Nomenclature> items) {
        User user = findById(id);
        items.forEach(item -> user.getItems().add(item));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void update(String id, UserData userData) {
        User user = updateUser(id, userData);
        sendMessagesIfNecessary(user, userData);
    }

    private void sendMessagesIfNecessary(User user, UserData userData) {
        if (userData.getPhoto() != null) {
            messageService.sendPhotoMessage(user.getId(), userData.getPhoto());
        }
        messageService.sendNotificationMessage(user);
    }

    private User updateUser(String id, UserData userData) {
        User user = findById(id);
        user.setName(userData.getName());
        user.setPhoneNumber(userData.getPhoneNumber());
        user.setEmail(userData.getEmail());
        user.setGender(userData.getGender());
        user.getSettings().setSubscription(userData.getSubscription());
        return userRepository.save(user);
    }
}
