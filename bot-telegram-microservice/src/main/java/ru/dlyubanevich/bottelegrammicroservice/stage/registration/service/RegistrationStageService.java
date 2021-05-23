package ru.dlyubanevich.bottelegrammicroservice.stage.registration.service;

import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateOrder;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.state.StateRegistration;

public interface RegistrationStageService {

    StateRegistration getUserState(Long userId);
    void setUserState(Long userId, StateRegistration state);
    void setNextUserState(Long userId);
    RegistrationDataModel getUserModel(Long userId);
    RegistrationDataModel addUserModel(Long userId);
    StateHandler<RegistrationDataModel> getStateHandler(StateRegistration state);
    StateHandler<RegistrationDataModel> getCurrentStateHandler(Long userId);
    boolean registrationComplete(Long userId);
    StateOrder<StateRegistration> getStateOrder();

}
