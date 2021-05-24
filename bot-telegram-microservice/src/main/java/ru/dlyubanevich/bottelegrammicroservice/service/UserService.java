package ru.dlyubanevich.bottelegrammicroservice.service;

import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;

public interface UserService {

    void saveUser(RegistrationDataModel registrationDataModel);
    boolean registrationComplete(Long telegramId);
    UserModel getUserByTelegramId(Long telegramId);

}
