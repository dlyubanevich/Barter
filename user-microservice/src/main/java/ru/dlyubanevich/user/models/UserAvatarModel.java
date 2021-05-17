package ru.dlyubanevich.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAvatarModel {

    private String ownerId;
    private List<String> items;

}
