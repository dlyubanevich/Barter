package ru.dlyubanevich.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;

@AllArgsConstructor
@Getter
public class UserData {

    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String gender;
    private final String photo;

    public User getUser(){
        User user = new User();
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setName(name);
        user.setGender(gender);
        user.setUserDetails(new UserDetails());
        return user;
    }
}
