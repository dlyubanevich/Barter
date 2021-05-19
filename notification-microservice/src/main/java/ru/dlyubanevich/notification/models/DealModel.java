package ru.dlyubanevich.notification.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.notification.domain.User;

@AllArgsConstructor
@Getter
public class DealModel {

    private final String id;
    private final User initiator;
    private final User partner;

}
