package ru.dlyubanevich.bottelegrammicroservice.stage.mainmenu.statehandler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainMenuStateHandler implements StateHandler<String> {

    private static final String TEXT = "Что вы хотите сделать?";

    private final ReplyKeyboardMarkup replyMarkup;

    public MainMenuStateHandler(List<String> menuItems){
        List<KeyboardButton> list = menuItems.stream().map(KeyboardButton::new).collect(Collectors.toList());
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
    public SendMessage buildReplyMessage(String model, Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(TEXT)
                .replyMarkup(replyMarkup)
                .build();
    }

    @Override
    public boolean process(String model, Update update) {
        return true;
    }
}
