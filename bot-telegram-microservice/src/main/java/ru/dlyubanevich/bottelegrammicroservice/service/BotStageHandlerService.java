package ru.dlyubanevich.bottelegrammicroservice.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dlyubanevich.bottelegrammicroservice.service.stage.RegistrationStageService;
import ru.dlyubanevich.bottelegrammicroservice.stage.Stage;
import ru.dlyubanevich.bottelegrammicroservice.stage.StageCommunication;
import ru.dlyubanevich.bottelegrammicroservice.stage.registration.RegistrationStage;

import java.util.HashMap;
import java.util.Map;

@Service
public class BotStageHandlerService implements StageHandlerService {

    private final Map<Long, Stage> userStage;
    private final Map<Stage, StageCommunication> stageCommunication;

    private final RegistrationStageService registrationStageService;

    public BotStageHandlerService(RegistrationStageService registrationStageService){
        this.registrationStageService = registrationStageService;
        this.userStage = new HashMap<>();
        this.stageCommunication = new HashMap<>();
        this.stageCommunication.put(Stage.REGISTRATION, new RegistrationStage(registrationStageService));
    }

    @Override
    public SendMessage handleCurrentStage(Message message) {
        Long userId = message.getFrom().getId();
        Stage currentStage = userStage.get(userId);
        if (currentStage == null && !userIsAlreadyRegistered(userId)) {
            currentStage = Stage.REGISTRATION;
        }

        StageCommunication stage = stageCommunication.get(currentStage);
        stage.handleMessage(message);
        SendMessage replyMessage = stage.getReplyMessage(message);
        if (stage.isCompleted(userId)){
            userStage.remove(userId);
        }else{
            userStage.put(userId, currentStage);
        }
        replyMessage.setChatId(message.getChatId().toString());

        return replyMessage;
    }

    private boolean userIsAlreadyRegistered(Long userId) {
        return registrationStageService.registrationComplete(userId);
    }

}
