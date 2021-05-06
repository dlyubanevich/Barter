package ru.dlyubanevich.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private double rating;
    private int bonusAccount;
    private String avatar;

}
