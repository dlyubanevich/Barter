package ru.dlyubanevich.bottelegrammicroservice.stage.mainmenu;

import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dlyubanevich.bottelegrammicroservice.stage.StageCommunication;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;
import ru.dlyubanevich.bottelegrammicroservice.stage.mainmenu.statehandler.MainMenuStateHandler;

import java.util.List;

@AllArgsConstructor
public class MainMenuStage implements StageCommunication {

    private final StateHandler<String> stateHandler;
    private final static String MODEL = "Главное меню";

    public MainMenuStage(List<String> menuItems){
        this.stateHandler = new MainMenuStateHandler(menuItems);
    }

    @Override
    public void handle(Update update) {
        stateHandler.process(MODEL, update);
    }

    @Override
    public BotApiMethod<?> getReplyMessage(Update update) {
        return stateHandler.buildReplyMessage(MODEL, update);
    }

    @Override
    public boolean isCompleted(Long userId) {
        return true;
    }
}
