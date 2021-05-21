package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;
import ru.dlyubanevich.bottelegrammicroservice.service.UserService;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

@RequiredArgsConstructor
public class SuccessRegistrationMessageHandler implements StateHandler<UserModel> {

    private static final String TEXT = "Поздравляю! Вы успешно прошли регистрацию.";

    private final UserService userService;

    @Override
    public boolean process(UserModel model, Message message) {
        userService.save(model);
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
