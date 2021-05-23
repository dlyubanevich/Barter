package ru.dlyubanevich.bottelegrammicroservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationDataModel {

    private String id;
    private String name;
    private String phoneNumber;
    private SubscriptionModel subscription;
    private Long telegramId;
    private Long chatId;

}
