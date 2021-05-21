package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

@RequiredArgsConstructor
public class AskUserNameStateHandler implements StateHandler<UserModel> {

    private static final String TEXT = "Введите, пожалуйста, вашу фамилию, имя и отчество:";

    @Override
    public boolean process(UserModel model, Message message) {
        String name = message.getText();
        model.setName(name);
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
