package ru.dlyubanevich.bottelegrammicroservice.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dlyubanevich.bottelegrammicroservice.stage.Stage;
import ru.dlyubanevich.bottelegrammicroservice.stage.StageCommunication;
import ru.dlyubanevich.bottelegrammicroservice.stage.mainmenu.MainMenuStage;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.RegistrationStage;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.service.RegistrationStageService;

import java.util.HashMap;
import java.util.Map;

@Service
public class BotStageHandlerService implements StageHandlerService {

    private final Map<Long, Stage> userStage;
    private final Map<Stage, StageCommunication> stageCommunication;

    private final BotService botService;
    private final UserService userService;

    public BotStageHandlerService(BotService botService, RegistrationStageService registrationStageService, UserService userService){
        this.userService = userService;
        this.botService = botService;
        this.userStage = new HashMap<>();
        this.stageCommunication = new HashMap<>();
        this.stageCommunication.put(Stage.REGISTRATION, new RegistrationStage(registrationStageService));
        this.stageCommunication.put(Stage.MAIN_MENU, new MainMenuStage(botService.getMainMenuItems()));
    }

    @Override
    public BotApiMethod<?> handleCurrentStage(Update update) {
        Long userId = getUserId(update);
        Stage currentStage = userStage.get(userId);

        if (currentStage == null){
            currentStage = botService.getStageByCommand(update.getMessage().getText());
            if (currentStage == null) {
                if (!userIsAlreadyRegistered(userId)) {
                    currentStage = Stage.REGISTRATION;
                } else {
                    currentStage = Stage.MAIN_MENU;
                }
            }
            userStage.put(userId, currentStage);
        }

        StageCommunication stage = stageCommunication.get(currentStage);
        stage.handle(update);
        BotApiMethod<?> replyMessage = stage.getReplyMessage(update);
        if (stage.isCompleted(userId)) {
            userStage.remove(userId);
        }

        return replyMessage;

    }

    private Long getUserId(Update update) {
        Long userId;
        if (update.hasCallbackQuery()) {
            userId = update.getCallbackQuery().getFrom().getId();
        }else {
            userId = update.getMessage().getFrom().getId();
        }
        return userId;
    }

    private boolean userIsAlreadyRegistered(Long userId) {
        return userService.registrationComplete(userId);
    }

}
