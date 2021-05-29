package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dlyubanevich.bottelegrammicroservice.model.RegistrationDataModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

@RequiredArgsConstructor
public class AskPhoneNumberStateHandler implements StateHandler<RegistrationDataModel> {

    private static final String TEXT = "Шаг 2 из 4.\nВведите, пожалуйста, ваш номер телефона (для связи после согласования сделки):";

    @Override
    public SendMessage buildReplyMessage(RegistrationDataModel model, Update update) {
        return SendMessage.builder()
                .chatId(model.getChatId().toString())
                .text(TEXT)
                .build();
    }

    @Override
    public boolean process(RegistrationDataModel model, Update update) {
        String phoneNumber = update.getMessage().getText();
        model.setPhoneNumber(phoneNumber);
        return true;
    }


}
