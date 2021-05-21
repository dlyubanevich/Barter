package ru.dlyubanevich.bottelegrammicroservice.stage;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface StateHandler<T> {

    boolean process(T model, Message message);
    SendMessage buildReplyMessage(T model, Message message);

}
