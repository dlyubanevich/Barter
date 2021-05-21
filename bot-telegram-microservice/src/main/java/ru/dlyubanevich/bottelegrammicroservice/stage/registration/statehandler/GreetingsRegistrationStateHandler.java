package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dlyubanevich.bottelegrammicroservice.model.SubscriptionModel;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

import java.util.ArrayList;

@RequiredArgsConstructor
public class GreetingsRegistrationStateHandler implements StateHandler<UserModel> {

    private static final String TEXT = "Здравствуйте! Необходимо пройти простую регистрацию. Она займет пару минут.";

    @Override
    public boolean process(UserModel model, Message message) {
        SubscriptionModel subscriptionModel = new SubscriptionModel();
        subscriptionModel.setOfferTypes(new ArrayList<>());
        subscriptionModel.setNomenclatureOptions(new ArrayList<>());
        model.setSubscription(subscriptionModel);
        return true;
    }

    @Override
    public SendMessage buildReplyMessage(UserModel model, Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .text(TEXT)
                .build();
    }

}
