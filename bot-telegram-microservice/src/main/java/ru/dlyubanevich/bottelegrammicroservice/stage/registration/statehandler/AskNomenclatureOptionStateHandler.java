package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.dlyubanevich.bottelegrammicroservice.model.NomenclatureOptionModel;
import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AskNomenclatureOptionStateHandler implements StateHandler<RegistrationDataModel> {

    private static final String TEXT = "Уведомления о каких товарах вы хотите получать?";
    private static final String ENOUGH = "Достаточно";

    private final ReplyKeyboardMarkup replyMarkup;

    private final Map<String, NomenclatureOptionModel> options;

    public AskNomenclatureOptionStateHandler(Map<String, NomenclatureOptionModel> options){

        List<KeyboardButton> list = options.keySet().stream()
                .map(KeyboardButton::new)
                .collect(Collectors.toList());
        list.add(new KeyboardButton(ENOUGH));
        KeyboardRow row = new KeyboardRow();
        row.addAll(list);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        this.options = options;
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
        String text = update.getMessage().getText();
        boolean isComplete = text.equals(ENOUGH);
        if (!isComplete){
            NomenclatureOptionModel nomenclatureOptionModel = options.get(text);
            List<NomenclatureOptionModel> currentOptions = model.getSubscription().getNomenclatureOptions();
            if (nomenclatureOptionModel != null && !currentOptions.contains(nomenclatureOptionModel)){
                currentOptions.add(nomenclatureOptionModel);
            }
        }
        return isComplete;
    }
}
