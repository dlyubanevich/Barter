package ru.dlyubanevich.bottelegrammicroservice.stage.registration;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;
import ru.dlyubanevich.bottelegrammicroservice.service.stage.RegistrationStageService;
import ru.dlyubanevich.bottelegrammicroservice.stage.StageCommunication;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.state.StateRegistration;

@RequiredArgsConstructor
public class RegistrationStage implements StageCommunication {

    private final RegistrationStageService registrationService;

    @Override
    public void handleMessage(Message message) {
        Long userId = message.getFrom().getId();
        StateRegistration state = getUserState(userId);
        boolean success = handleCurrentState(userId, state, message);
        if (success){
            registrationService.setNextUserState(userId);
        }
    }

    private StateRegistration getUserState(Long userId) {
        StateRegistration state = registrationService.getUserState(userId);
        if (state == null){
            state = registrationService.getStateOrder().getFirstState();
            registrationService.setUserState(userId, state);
        }
        return state;
    }

    private boolean handleCurrentState(Long userId, StateRegistration state, Message message) {
        StateHandler<UserModel> handler = registrationService.getStateHandler(state);
        if (handler != null) {
            UserModel model = getUserModel(userId);
            return handler.process(model, message);
        }else{
            return true;
        }
    }

    private UserModel getUserModel(Long userId) {
        UserModel model = registrationService.getUserModel(userId);
        if (model == null){
            model = registrationService.addUserModel(userId);
        }
        return model;
    }

    @Override
    public SendMessage getReplyMessage(Message message) {
        Long userId = message.getFrom().getId();
        StateHandler<UserModel> handler = registrationService.getCurrentStateHandler(userId);
        UserModel model = registrationService.getUserModel(userId);
        return handler.buildReplyMessage(model, message);
    }

    @Override
    public boolean isCompleted(Long userId) {
        return registrationService.registrationComplete(userId);
    }

}
