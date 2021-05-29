package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AskOfferTypeStateHandler implements StateHandler<RegistrationDataModel> {

    private static final String TEXT = "Шаг 4 из 4.\nУведомления о каких видах предложений вы хотите получать?";
    private static final String ENOUGH = "Достаточно";

    private final ReplyKeyboardMarkup replyMarkup;

    public AskOfferTypeStateHandler(List<String> offerTypes){
        List<KeyboardButton> list = offerTypes.stream()
                .map(KeyboardButton::new)
                .collect(Collectors.toList());
        list.add(new KeyboardButton(ENOUGH));
        KeyboardRow row = new KeyboardRow();
        row.addAll(list);
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
        return SendMessage.builder()
                .chatId(model.getChatId().toString())
                .text(TEXT)
                .replyMarkup(replyMarkup)
                .build();
    }

    @Override
    public boolean process(RegistrationDataModel model, Update update) {
        String offerType = update.getMessage().getText();
        boolean isComplete = (offerType.equals(ENOUGH));
        if (!isComplete) {
            List<String> currentOfferTypes = model.getSubscription().getOfferTypes();
            if (!currentOfferTypes.contains(offerType)) {
                currentOfferTypes.add(offerType);
            }
        }
        return isComplete;
    }
}
