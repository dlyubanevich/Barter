package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.model.SubscriptionModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

import java.util.ArrayList;
import java.util.List;

public class GreetingsRegistrationStateHandler implements StateHandler<RegistrationDataModel> {

    private static final String TEXT = "Здравствуйте!\nНеобходимо пройти простую регистрацию, состоящую из нескольких шагов.";
    private static final String ANSWER = "Хорошо";

    private final ReplyKeyboardMarkup replyMarkup;

    public GreetingsRegistrationStateHandler(){

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(ANSWER));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        this.replyMarkup = ReplyKeyboardMarkup.builder()
                .selective(true)
                .oneTimeKeyboard(true)
                .keyboard(keyboard)
                .build();

    }

    @Override
    public SendMessage buildReplyMessage(RegistrationDataModel model, Update update) {
        String chatId;
        if(update.hasMessage()){
            chatId = update.getMessage().getChatId().toString();
        }else{
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        }
        return SendMessage.builder()
                .chatId(chatId)
                .text(TEXT)
                .replyMarkup(replyMarkup)
                .build();
    }

    @Override
    public boolean process(RegistrationDataModel model, Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        if (text.equals(ANSWER)) {
            SubscriptionModel subscriptionModel = new SubscriptionModel();
            subscriptionModel.setOfferTypes(new ArrayList<>());
            subscriptionModel.setNomenclatureOptions(new ArrayList<>());
            model.setSubscription(subscriptionModel);
            model.setTelegramId(message.getFrom().getId());
            model.setChatId(message.getChatId());
            return true;
        }else{
            return false;
        }
    }

}
