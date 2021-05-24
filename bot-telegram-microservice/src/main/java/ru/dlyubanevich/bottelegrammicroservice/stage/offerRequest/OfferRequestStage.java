package ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dlyubanevich.bottelegrammicroservice.model.OfferRequestDataModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StageCommunication;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.service.OfferRequestStageService;
import ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.state.StateOfferRequest;

@RequiredArgsConstructor
public class OfferRequestStage implements StageCommunication {

    private final OfferRequestStageService offerRequestStageService;

    @Override
    public void handle(Update update) {
        Long userId = getUserId(update);
        StateOfferRequest state = getUserState(userId);
        boolean success = handleCurrentState(userId, state, update);
        if (success){
            offerRequestStageService.setNextState(userId);
        }else {
            if (state == StateOfferRequest.SAVE_OFFER_REQUEST){
                offerRequestStageService.setState(userId, StateOfferRequest.GREETINGS_FOR_OFFER_REQUEST);
            }
        }
    }

    private boolean handleCurrentState(Long userId, StateOfferRequest state, Update update) {
        StateHandler<OfferRequestDataModel> handler = offerRequestStageService.getStateHandler(state);
        if (handler != null) {
            OfferRequestDataModel model = getOfferRequestModel(userId);
            return handler.process(model, update);
        }else{
            return true;
        }
    }

    private OfferRequestDataModel getOfferRequestModel(Long userId) {
        OfferRequestDataModel model = offerRequestStageService.getModel(userId);
        if (model == null){
            model = offerRequestStageService.addModel(userId);
        }
        return model;
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

    private StateOfferRequest getUserState(Long userId) {
        StateOfferRequest state = offerRequestStageService.getUserState(userId);
        if (state == null){
            state = offerRequestStageService.getStateOrder().getFirstState();
            offerRequestStageService.setState(userId, state);
        }
        return state;
    }

    @Override
    public BotApiMethod<?> getReplyMessage(Update update) {
        Long userId = getUserId(update);
        StateHandler<OfferRequestDataModel> handler = offerRequestStageService.getCurrentStateHandler(userId);
        OfferRequestDataModel model = offerRequestStageService.getModel(userId);
        return handler.buildReplyMessage(model, update);
    }

    @Override
    public boolean isCompleted(Long userId) {
        StateOfferRequest state = offerRequestStageService.getUserState(userId);
        return (state == StateOfferRequest.SUCCESS_FOR_OFFER_REQUEST);
    }

}
