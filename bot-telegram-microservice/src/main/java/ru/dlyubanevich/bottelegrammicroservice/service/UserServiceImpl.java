package ru.dlyubanevich.bottelegrammicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.bottelegrammicroservice.domain.User;
import ru.dlyubanevich.bottelegrammicroservice.feign.UserServiceFeignClient;
import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;
import ru.dlyubanevich.bottelegrammicroservice.model.UserRegistrationModel;
import ru.dlyubanevich.bottelegrammicroservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserServiceFeignClient userServiceFeignClient;
    private final UserRepository repository;

    @Transactional
    @Override
    public void saveUser(RegistrationDataModel registrationDataModel) {
        UserRegistrationModel userRegistrationModel = new UserRegistrationModel(registrationDataModel);
        String id = userServiceFeignClient.addUser(userRegistrationModel);
        User user = new User(
                id,
                registrationDataModel.getName(),
                registrationDataModel.getPhoneNumber(),
                registrationDataModel.getTelegramId(),
                registrationDataModel.getChatId()
        );
        repository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean registrationComplete(Long telegramId) {
        return repository.existsUserByTelegramId(telegramId);
    }

    //TODO Spring Cache
    @Override
    public UserModel getUserByTelegramId(Long telegramId) {
        User user = repository.findByTelegramId(telegramId);
        return new UserModel(user.getId(), user.getName());
    }

}
