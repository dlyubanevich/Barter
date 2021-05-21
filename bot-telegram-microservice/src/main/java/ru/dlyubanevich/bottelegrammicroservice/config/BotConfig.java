package ru.dlyubanevich.bottelegrammicroservice.config;

import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import ru.dlyubanevich.bottelegrammicroservice.BarterTelegramBot;
import ru.dlyubanevich.bottelegrammicroservice.service.BotStageHandlerService;
import ru.dlyubanevich.bottelegrammicroservice.service.StageHandlerService;
import ru.dlyubanevich.bottelegrammicroservice.service.stage.RegistrationStageServiceImpl;

@Configuration
public class BotConfig {


}
