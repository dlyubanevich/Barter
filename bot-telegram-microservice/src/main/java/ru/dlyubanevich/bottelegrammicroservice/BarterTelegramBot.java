package ru.dlyubanevich.bottelegrammicroservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dlyubanevich.bottelegrammicroservice.service.StageHandlerService;

@RequiredArgsConstructor
@Component
public class BarterTelegramBot extends TelegramWebhookBot {

    @Value("${webHookPath}")
    private String webHookPath;
    @Value("${botUserName}")
    private String botUserName;
    @Value("${botToken}")
    private String botToken;

    private final StageHandlerService stageHandlerService;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return stageHandlerService.handleCurrentStage(update);
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}
