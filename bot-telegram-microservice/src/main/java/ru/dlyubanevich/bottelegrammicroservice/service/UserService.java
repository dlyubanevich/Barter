package ru.dlyubanevich.bottelegrammicroservice.service;

import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;

public interface UserService {

    void saveUser(RegistrationDataModel registrationDataModel);
    boolean registrationComplete(Long telegramId);

}
