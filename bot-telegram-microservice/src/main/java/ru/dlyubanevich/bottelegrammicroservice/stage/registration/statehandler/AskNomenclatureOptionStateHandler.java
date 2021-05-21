package ru.dlyubanevich.bottelegrammicroservice.stage.registration.statehandler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dlyubanevich.bottelegrammicroservice.model.NomenclatureOptionModel;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;
import ru.dlyubanevich.bottelegrammicroservice.stage.StateHandler;

import java.util.Map;

@RequiredArgsConstructor
public class AskNomenclatureOptionStateHandler implements StateHandler<UserModel> {

    private static final String TEXT = "Выберите виды товаров, предложения с которыми Вы хотите получать:";

    private final Map<String, NomenclatureOptionModel> options;

    @Override
    public boolean process(UserModel model, Message message) {
        String text = message.getText();
        NomenclatureOptionModel nomenclatureOptionModel = options.get(text);
        boolean isCorrectOption = (nomenclatureOptionModel != null);
        if (isCorrectOption){
            model.getSubscription().getNomenclatureOptions().add(nomenclatureOptionModel);
        }
        return !isCorrectOption;
    }

    @Override
    public SendMessage buildReplyMessage(UserModel model, Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .text(TEXT)
                .build();
    }

}
