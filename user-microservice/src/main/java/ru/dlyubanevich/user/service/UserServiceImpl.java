package ru.dlyubanevich.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;
import ru.dlyubanevich.user.models.UserData;
import ru.dlyubanevich.user.models.UserPhoto;
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
        User savedUser = userRepository.save(userData.getUser());
        messageService.sendPhotoMessage(savedUser.getId(), userData.getPhoto());
        return savedUser;
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
        user.setUserDetails(details);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void addUserAvatar(String id, String avatar) {
        User user = findById(id);
        user.getUserDetails().setAvatar(avatar);
        userRepository.save(user);
    }
}
