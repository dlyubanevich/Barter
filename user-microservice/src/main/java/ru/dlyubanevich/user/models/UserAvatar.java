package ru.dlyubanevich.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserAvatar {

    private final String userId;
    private final String url;

}
