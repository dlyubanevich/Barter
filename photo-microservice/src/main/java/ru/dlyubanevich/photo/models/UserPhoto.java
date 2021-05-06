package ru.dlyubanevich.photo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPhoto {

    private final String userId;
    private final String photo;

}
