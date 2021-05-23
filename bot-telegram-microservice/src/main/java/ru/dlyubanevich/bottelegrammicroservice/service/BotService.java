package ru.dlyubanevich.bottelegrammicroservice.service;

import ru.dlyubanevich.bottelegrammicroservice.stage.Stage;

import java.util.List;

public interface BotService {

    List<String> getMainMenuItems();
    Stage getStageByCommand(String command);

}
