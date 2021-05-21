package ru.dlyubanevich.bottelegrammicroservice.stage;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface StageCommunication {

    boolean isCompleted(Long userId);
    void handleMessage(Message message);
    SendMessage getReplyMessage(Message message);

}
