package ru.dlyubanevich.bottelegrammicroservice.service;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface StageHandlerService {

    BotApiMethod<?> handleCurrentStage(Update update);

}
