package ru.dlyubanevich.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.user.domain.Subscription;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;
import ru.dlyubanevich.user.domain.UserSettings;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDataModel {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String gender;
    private FileModel photo;
    private Subscription subscription;

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
