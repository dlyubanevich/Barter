package ru.dlyubanevich.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.user.domain.Subscription;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;
import ru.dlyubanevich.user.domain.UserSettings;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class UserData {

    private final String id;
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String gender;
    private final String photo;
    private final Subscription subscription;

    public User getUser(){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setName(name);
        user.setGender(gender);
        user.setDetails(new UserDetails());
        user.setSettings(new UserSettings(subscription));
        user.setItems(new ArrayList<>());
        return user;
    }
}
