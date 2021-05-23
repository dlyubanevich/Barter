package ru.dlyubanevich.bottelegrammicroservice.stage.registration;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.service.RegistrationStageService;
import ru.dlyubanevich.bottelegrammicroservice.stage.StageCommunication;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.state.StateRegistration;

@RequiredArgsConstructor
public class RegistrationStage implements StageCommunication {

    private final RegistrationStageService registrationService;

    @Override
    public void handle(Update update) {
        Long userId = getUserId(update);
        StateRegistration state = getUserState(userId);
        boolean success = handleCurrentState(userId, state, update);
        if (success){
            registrationService.setNextUserState(userId);
        }else {
            if (state == StateRegistration.SAVE_USER_MODEL){
                registrationService.setUserState(userId, StateRegistration.GREETINGS_FOR_REGISTRATION);
            }
        }
    }

    private Long getUserId(Update update) {
        Long userId;
        if (update.hasCallbackQuery()) {
            userId = update.getCallbackQuery().getFrom().getId();
        }else {
            userId = update.getMessage().getFrom().getId();
        }
        return userId;
    }

    private StateRegistration getUserState(Long userId) {
        StateRegistration state = registrationService.getUserState(userId);
        if (state == null){
            state = registrationService.getStateOrder().getFirstState();
            registrationService.setUserState(userId, state);
        }
        return state;
    }

    private boolean handleCurrentState(Long userId, StateRegistration state, Update update) {
        StateHandler<RegistrationDataModel> handler = registrationService.getStateHandler(state);
        if (handler != null) {
            RegistrationDataModel model = getRegistrationDataModel(userId);
            return handler.process(model, update);
        }else{
            return true;
        }
    }

    private RegistrationDataModel getRegistrationDataModel(Long userId) {
        RegistrationDataModel model = registrationService.getUserModel(userId);
        if (model == null){
            model = registrationService.addUserModel(userId);
        }
        return model;
    }

    @Override
    public BotApiMethod<?> getReplyMessage(Update update) {
        Long userId = getUserId(update);
        StateHandler<RegistrationDataModel> handler = registrationService.getCurrentStateHandler(userId);
        RegistrationDataModel model = registrationService.getUserModel(userId);
        return handler.buildReplyMessage(model, update);
    }

    @Override
    public boolean isCompleted(Long userId) {
        return registrationService.registrationComplete(userId);
    }

}
