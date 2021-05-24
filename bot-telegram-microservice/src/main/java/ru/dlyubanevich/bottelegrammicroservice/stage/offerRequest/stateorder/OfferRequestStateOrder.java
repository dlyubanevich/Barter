package ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.stateorder;

import ru.dlyubanevich.bottelegrammicroservice.stage.StateOrder;
import ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.state.StateOfferRequest;

import java.util.HashMap;
import java.util.Map;

public class OfferRequestStateOrder implements StateOrder<StateOfferRequest> {

    private final Map<StateOfferRequest, StateOfferRequest> orderStates;

    public OfferRequestStateOrder(){
        orderStates = new HashMap<>();
        orderStates.put(StateOfferRequest.GREETINGS_FOR_OFFER_REQUEST, StateOfferRequest.ASK_OFFER_TYPE);
        orderStates.put(StateOfferRequest.ASK_OFFER_TYPE, StateOfferRequest.ASK_OFFER_OPTION);
        orderStates.put(StateOfferRequest.ASK_OFFER_OPTION, StateOfferRequest.ASK_NOMENCLATURE_TYPE);
        orderStates.put(StateOfferRequest.ASK_NOMENCLATURE_TYPE, StateOfferRequest.ASK_NOMENCLATURE_OPTION);
        orderStates.put(StateOfferRequest.ASK_NOMENCLATURE_OPTION, StateOfferRequest.ASK_NOMENCLATURE_NAME);
        orderStates.put(StateOfferRequest.ASK_NOMENCLATURE_NAME, StateOfferRequest.ASK_NOMENCLATURE_DESCRIPTION);
        orderStates.put(StateOfferRequest.ASK_NOMENCLATURE_DESCRIPTION, StateOfferRequest.ASK_NOMENCLATURE_PHOTO);
        orderStates.put(StateOfferRequest.ASK_NOMENCLATURE_PHOTO, StateOfferRequest.ASK_OFFER_DESCRIPTION);
        orderStates.put(StateOfferRequest.ASK_OFFER_DESCRIPTION, StateOfferRequest.ASK_OFFER_REQUIREMENTS);
        orderStates.put(StateOfferRequest.ASK_OFFER_REQUIREMENTS, StateOfferRequest.SAVE_OFFER_REQUEST);
        orderStates.put(StateOfferRequest.SAVE_OFFER_REQUEST, StateOfferRequest.SUCCESS_FOR_OFFER_REQUEST);
    }

    @Override
    public StateOfferRequest getFirstState() {
        return StateOfferRequest.GREETINGS_FOR_OFFER_REQUEST;
    }

    @Override
    public StateOfferRequest getNextState(StateOfferRequest state) {
        return orderStates.get(state);
    }

    @Override
    public StateOfferRequest getLastState() {
        return StateOfferRequest.SUCCESS_FOR_OFFER_REQUEST;
    }

}
