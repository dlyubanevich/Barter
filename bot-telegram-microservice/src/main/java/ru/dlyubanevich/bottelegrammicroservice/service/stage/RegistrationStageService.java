package ru.dlyubanevich.bottelegrammicroservice.service.stage;

import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateOrder;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.state.StateRegistration;

public interface RegistrationStageService {

    StateRegistration getUserState(Long userId);
    void setUserState(Long userId, StateRegistration state);
    void setNextUserState(Long userId);
    UserModel getUserModel(Long userId);
    UserModel addUserModel(Long userId);
    StateHandler<UserModel> getStateHandler(StateRegistration state);
    StateHandler<UserModel> getCurrentStateHandler(Long userId);
    boolean registrationComplete(Long userId);
    StateOrder<StateRegistration> getStateOrder();

}
