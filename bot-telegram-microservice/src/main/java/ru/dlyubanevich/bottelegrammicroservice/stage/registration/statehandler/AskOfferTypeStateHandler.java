package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

import java.util.List;

@RequiredArgsConstructor
public class AskOfferTypeStateHandler implements StateHandler<UserModel> {

    private static final String TEXT = "Выберите виды предложений, уведомления о которых Вы хотите получать:";

    private final List<String> offerTypes;

    @Override
    public boolean process(UserModel model, Message message) {
        String offerType = message.getText();
        boolean isCorrectOfferType = (offerTypes.contains(offerType));
        List<String> currentOfferTypes = model.getSubscription().getOfferTypes();
        if (isCorrectOfferType && !currentOfferTypes.contains(offerType)){
            currentOfferTypes.add(offerType);
        }
        return !isCorrectOfferType;
    }

    @Override
    public SendMessage buildReplyMessage(UserModel model, Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .text(TEXT)
                .build();
    }

}
