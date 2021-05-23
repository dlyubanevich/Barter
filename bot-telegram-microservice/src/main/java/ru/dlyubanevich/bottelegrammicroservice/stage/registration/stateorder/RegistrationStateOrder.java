package ru.dlyubanevich.bottelegrammicroservice.stage.registration.stateorder;

import ru.dlyubanevich.bottelegrammicroservice.stage.StateOrder;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.state.StateRegistration;

import java.util.HashMap;
import java.util.Map;

public class RegistrationStateOrder implements StateOrder<StateRegistration> {

    private final Map<StateRegistration, StateRegistration> orderStates;

    public RegistrationStateOrder(){
        orderStates = new HashMap<>();
        orderStates.put(StateRegistration.GREETINGS_FOR_REGISTRATION, StateRegistration.ASK_USER_NAME);
        orderStates.put(StateRegistration.ASK_USER_NAME, StateRegistration.ASK_PHONE_NUMBER);
        orderStates.put(StateRegistration.ASK_PHONE_NUMBER, StateRegistration.ASK_NOMENCLATURE_OPTION_SUBSCRIPTION);
        orderStates.put(StateRegistration.ASK_NOMENCLATURE_OPTION_SUBSCRIPTION, StateRegistration.ASK_OFFER_TYPE_SUBSCRIPTION);
        orderStates.put(StateRegistration.ASK_OFFER_TYPE_SUBSCRIPTION, StateRegistration.SAVE_USER_MODEL);
        orderStates.put(StateRegistration.SAVE_USER_MODEL, StateRegistration.CONGRATS_WITH_SUCCESS_REGISTRATION);
    }

    @Override
    public StateRegistration getFirstState() {
        return StateRegistration.GREETINGS_FOR_REGISTRATION;
    }

    @Override
    public StateRegistration getNextState(StateRegistration state) {
        return orderStates.get(state);
    }

    @Override
    public StateRegistration getLastState() {
        return StateRegistration.CONGRATS_WITH_SUCCESS_REGISTRATION;
    }
}
