package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.dlyubanevich.bottelegrammicroservice.model.NomenclatureOptionModel;
import ru.dlyubanevich.bottelegrammicroservice.model.SubscriptionModel;
import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.service.UserService;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaveUserModelStateHandler implements StateHandler<RegistrationDataModel> {

    private final static String CALLBACK_CONFIRM = "Confirm";
    private final static String CALLBACK_NEGATIVE = "Negative";

    private final UserService userService;
    private final InlineKeyboardMarkup keyboard;

    public SaveUserModelStateHandler(UserService userService){

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonYes = new InlineKeyboardButton();
        buttonYes.setText("Да, все верно");
        buttonYes.setCallbackData(CALLBACK_CONFIRM);

        InlineKeyboardButton buttonNo = new InlineKeyboardButton();
        buttonNo.setText("Нет, есть ошибки");
        buttonNo.setCallbackData(CALLBACK_NEGATIVE);

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(buttonYes);
        keyboardButtonsRow.add(buttonNo);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);

        keyboard.setKeyboard(rowList);

        this.userService = userService;
        this.keyboard = keyboard;

    }

    @Override
    public SendMessage buildReplyMessage(RegistrationDataModel model, Update update) {
        String text = getRegistrationData(model);
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(text)
                .replyMarkup(keyboard)
                .build();
    }

    private String getRegistrationData(RegistrationDataModel model) {
        SubscriptionModel subscriptions = model.getSubscription();
        return "Ваши регистрационные данные:"
                + "\n___________________________________"
                + "\nФИО: " + model.getName()
                + "\nТелефон: " + model.getPhoneNumber()
                + "\nИнтересующие виды товаров: " + String.join(",", subscriptions.getOfferTypes())
                + "\nИнтересующие виды предложений: " + subscriptions.getNomenclatureOptions().stream()
                    .map(NomenclatureOptionModel::getName)
                    .collect(Collectors.joining(","))
                + "\nВсе верно?";
    }

    @Override
    public boolean process(RegistrationDataModel model, Update update) {
        if (update.hasCallbackQuery()){
            String callback = update.getCallbackQuery().getData();
            if (callback.equals(CALLBACK_CONFIRM)){
                userService.saveUser(model);
                return true;
            }
        }
        return false;
    }

}
