package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

@RequiredArgsConstructor
public class AskUserNameStateHandler implements StateHandler<RegistrationDataModel> {

    private static final String TEXT = "Введите, пожалуйста, вашу фамилию, имя и отчество:";

    @Override
    public SendMessage buildReplyMessage(RegistrationDataModel model, Update update) {
        return SendMessage.builder()
                .chatId(model.getChatId().toString())
                .text(TEXT)
                .build();
    }

    @Override
    public boolean process(RegistrationDataModel model, Update update) {
        String name = update.getMessage().getText();
        model.setName(name);
        return true;
    }

}
