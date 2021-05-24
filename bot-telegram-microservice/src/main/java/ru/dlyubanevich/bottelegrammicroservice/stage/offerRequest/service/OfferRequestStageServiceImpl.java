package ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.service;

import org.springframework.stereotype.Service;
import ru.dlyubanevich.bottelegrammicroservice.model.OfferRequestDataModel;
import ru.dlyubanevich.bottelegrammicroservice.service.NomenclatureService;
import ru.dlyubanevich.bottelegrammicroservice.service.UserService;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateOrder;
import ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.state.StateOfferRequest;
import ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.statehandler.GreetingsOfferRequestStateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.stateorder.OfferRequestStateOrder;

import java.util.HashMap;
import java.util.Map;

@Service
public class OfferRequestStageServiceImpl implements OfferRequestStageService {

    private final NomenclatureService nomenclatureService;

    private final Map<Long, StateOfferRequest> usersState = new HashMap<>();
    private final Map<StateOfferRequest, StateHandler<OfferRequestDataModel>> stateHandlers = new HashMap<>();
    private final StateOrder<StateOfferRequest> stateOrder = new OfferRequestStateOrder();
    private final Map<Long, OfferRequestDataModel> usersModels = new HashMap<>();

    public OfferRequestStageServiceImpl(NomenclatureService nomenclatureService, UserService userService){
        this.nomenclatureService = nomenclatureService;
        stateHandlers.put(
                StateOfferRequest.GREETINGS_FOR_OFFER_REQUEST,
                new GreetingsOfferRequestStateHandler(userService)
        );
    }

    @Override
    public StateOfferRequest getUserState(Long userId) {
        return usersState.get(userId);
    }

    @Override
    public void setState(Long userId, StateOfferRequest state) {
        usersState.put(userId, state);
    }

    @Override
    public void setNextState(Long userId) {
        StateOfferRequest state = getUserState(userId);
        StateOfferRequest nextState = stateOrder.getNextState(state);
        setState(userId, nextState);
    }

    @Override
    public OfferRequestDataModel getModel(Long userId) {
        return usersModels.get(userId);
    }

    @Override
    public OfferRequestDataModel addModel(Long userId) {
        OfferRequestDataModel model = new OfferRequestDataModel();
        usersModels.put(userId, model);
        return model;
    }

    @Override
    public StateHandler<OfferRequestDataModel> getStateHandler(StateOfferRequest state) {
        return stateHandlers.get(state);
    }

    @Override
    public StateHandler<OfferRequestDataModel> getCurrentStateHandler(Long userId) {
        StateOfferRequest state = getUserState(userId);
        return getStateHandler(state);
    }

    @Override
    public StateOrder<StateOfferRequest> getStateOrder() {
        return stateOrder;
    }

}
