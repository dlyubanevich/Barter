package ru.dlyubanevich.bottelegrammicroservice.service.stage;

import org.springframework.stereotype.Service;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;
import ru.dlyubanevich.bottelegrammicroservice.service.NomenclatureService;
import ru.dlyubanevich.bottelegrammicroservice.service.OfferService;
import ru.dlyubanevich.bottelegrammicroservice.service.UserService;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateOrder;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.state.StateRegistration;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler.*;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.stateorder.RegistrationStateOrder;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegistrationStageServiceImpl implements RegistrationStageService {

    private final NomenclatureService nomenclatureService;
    private final UserService userService;
    private final OfferService offerService;

    private final Map<Long, StateRegistration> usersState = new HashMap<>();
    private final Map<Long, UserModel> usersModels = new HashMap<>();
    private final Map<StateRegistration, StateHandler<UserModel>> stateHandlers = new HashMap<>();
    private final StateOrder<StateRegistration> stateOrder = new RegistrationStateOrder();

    public RegistrationStageServiceImpl(UserService userService, NomenclatureService nomenclatureService, OfferService offerService){
        this.nomenclatureService = nomenclatureService;
        this.userService = userService;
        this.offerService = offerService;
        initializeStateHandlers();
    }

    private void initializeStateHandlers() {
        stateHandlers.put(
                StateRegistration.GREETINGS_FOR_REGISTRATION,
                new GreetingsRegistrationStateHandler()
        );
        stateHandlers.put(
                StateRegistration.ASK_USER_NAME,
                new AskUserNameStateHandler()
        );
        stateHandlers.put(
                StateRegistration.ASK_PHONE_NUMBER,
                new AskPhoneNumberStateHandler()
        );
        stateHandlers.put(
                StateRegistration.ASK_NOMENCLATURE_OPTION_SUBSCRIPTION,
                new AskNomenclatureOptionStateHandler(nomenclatureService.getNomenclatureOptions())
        );
        stateHandlers.put(
                StateRegistration.ASK_OFFER_TYPE_SUBSCRIPTION,
                new AskOfferTypeStateHandler(offerService.getOfferTypes())
        );
        stateHandlers.put(
                StateRegistration.CONGRATS_WITH_SUCCESS_REGISTRATION,
                new SuccessRegistrationMessageHandler(userService)
        );
    }

    @Override
    public StateRegistration getUserState(Long userId) {
        return usersState.get(userId);
    }

    @Override
    public void setUserState(Long userId, StateRegistration state) {
        usersState.put(userId, state);
    }

    @Override
    public UserModel addUserModel(Long userId) {
        UserModel userModel = new UserModel();
        usersModels.put(userId, userModel);
        return userModel;
    }

    @Override
    public void setNextUserState(Long userId) {
        StateRegistration state = getUserState(userId);
        StateRegistration nextState = stateOrder.getNextState(state);
        setUserState(userId, nextState);
    }

    @Override
    public UserModel getUserModel(Long userId) {
        return usersModels.get(userId);
    }

    @Override
    public StateHandler<UserModel> getStateHandler(StateRegistration state) {
        return stateHandlers.get(state);
    }

    @Override
    public StateHandler<UserModel> getCurrentStateHandler(Long userId) {
        StateRegistration state = getUserState(userId);
        return getStateHandler(state);
    }

    @Override
    public boolean registrationComplete(Long userId) {
        StateRegistration state = getUserState(userId);
        return (state != null && state == stateOrder.getLastState());
    }

    @Override
    public StateOrder<StateRegistration> getStateOrder() {
        return stateOrder;
    }
}
