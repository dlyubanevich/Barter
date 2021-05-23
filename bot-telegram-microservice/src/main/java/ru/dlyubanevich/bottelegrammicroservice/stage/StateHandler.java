package ru.dlyubanevich.bottelegrammicroservice.stage;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface StateHandler<T> {

    SendMessage buildReplyMessage(T model, Update update);
    boolean process(T model, Update update);

}
