package ru.dlyubanevich.bottelegrammicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String id;
    private String name;
    private String phoneNumber;
    private SubscriptionModel subscription;

    public UserModel(RegistrationDataModel registrationDataModel){
        this.name = registrationDataModel.getName();
        this.phoneNumber = registrationDataModel.getPhoneNumber();
        this.subscription = registrationDataModel.getSubscription();
    }
}
