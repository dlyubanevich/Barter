package ru.dlyubanevich.bottelegrammicroservice.stage;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface StageCommunication {

    boolean isCompleted(Long userId);
    void handle(Update update);
    BotApiMethod<?> getReplyMessage(Update update);

}
