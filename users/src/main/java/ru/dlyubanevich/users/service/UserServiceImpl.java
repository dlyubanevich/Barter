package ru.dlyubanevich.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.users.domain.User;
import ru.dlyubanevich.users.domain.UserDetails;
import ru.dlyubanevich.users.repository.UserRepository;
import ru.dlyubanevich.users.service.exception.UserNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
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

}
