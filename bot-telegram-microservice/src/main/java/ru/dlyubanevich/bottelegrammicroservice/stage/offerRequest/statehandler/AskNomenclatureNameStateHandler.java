package ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.statehandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dlyubanevich.bottelegrammicroservice.model.OfferRequestDataModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

public class AskNomenclatureNameStateHandler implements StateHandler<OfferRequestDataModel> {

    @Override
    public SendMessage buildReplyMessage(OfferRequestDataModel model, Update update) {
        return null;
    }

    @Override
    public boolean process(OfferRequestDataModel model, Update update) {
        return false;
    }
}
