package ru.dlyubanevich.bottelegrammicroservice.service;

import org.springframework.stereotype.Service;
import ru.dlyubanevich.bottelegrammicroservice.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BotServiceImpl implements BotService {

    private final Map<String, Stage> commands = new HashMap<>();

    public BotServiceImpl(){
        commands.put("Создать новое предложение", Stage.OFFER_REQUEST);
    }

    @Override
    public List<String> getMainMenuItems() {
        return new ArrayList<>(commands.keySet());
    }

    @Override
    public Stage getStageByCommand(String command) {
        return commands.get(command);
    }
}
