package ru.dlyubanevich.bottelegrammicroservice.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface StageHandlerService {

    SendMessage handleCurrentStage(Message message);

}
