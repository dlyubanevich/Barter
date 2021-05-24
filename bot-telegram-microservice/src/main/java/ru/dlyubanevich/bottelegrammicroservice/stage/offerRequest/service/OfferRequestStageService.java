package ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.service;

import ru.dlyubanevich.bottelegrammicroservice.model.OfferRequestDataModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateOrder;
import ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.state.StateOfferRequest;

public interface OfferRequestStageService {

    StateOfferRequest getUserState(Long userId);
    void setState(Long userId, StateOfferRequest state);
    void setNextState(Long userId);
    OfferRequestDataModel getModel(Long userId);
    OfferRequestDataModel addModel(Long userId);
    StateHandler<OfferRequestDataModel> getStateHandler(StateOfferRequest state);
    StateHandler<OfferRequestDataModel> getCurrentStateHandler(Long userId);
    StateOrder<StateOfferRequest> getStateOrder();

}
