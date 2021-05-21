package ru.dlyubanevich.bottelegrammicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String id;
    private String name;
    private String phoneNumber;
    private SubscriptionModel subscription;

}
