package ru.dlyubanevich.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSettings {

    private Subscription subscription;

}
