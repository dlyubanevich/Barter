package ru.dlyubanevich.bottelegrammicroservice.stage.offerRequest.statehandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.dlyubanevich.bottelegrammicroservice.model.OfferRequestDataModel;
import ru.dlyubanevich.bottelegrammicroservice.service.UserService;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

import java.util.ArrayList;
import java.util.List;

public class GreetingsOfferRequestStateHandler implements StateHandler<OfferRequestDataModel> {

    private static final String TEXT = "Переходим к созданию нового предложения для обмена товарами?";
    private static final String CONFIRM = "Да";
    private static final String ABORT = "Нет";

    private final ReplyKeyboardMarkup replyMarkup;
    private final UserService userService;

    public GreetingsOfferRequestStateHandler(UserService userService){
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(CONFIRM));
        row.add(new KeyboardButton(ABORT));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        this.replyMarkup = ReplyKeyboardMarkup.builder()
                .selective(true)
                .oneTimeKeyboard(true)
                .keyboard(keyboard)
                .build();
        this.userService = userService;

    }

    @Override
    public SendMessage buildReplyMessage(OfferRequestDataModel model, Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(TEXT)
                .replyMarkup(replyMarkup)
                .build();
    }

    @Override
    public boolean process(OfferRequestDataModel model, Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        if (text.equals(CONFIRM)) {
            model.setUser(userService.getUserByTelegramId(message.getFrom().getId()));
            model.setItems(new ArrayList<>());
            model.setRequirements(new ArrayList<>());
            return true;
        }else{
            return false;
        }
    }

}
