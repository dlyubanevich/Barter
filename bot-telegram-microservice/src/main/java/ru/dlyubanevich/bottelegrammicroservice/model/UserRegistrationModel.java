package ru.dlyubanevich.bottelegrammicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModel {

    private String name;
    private String phoneNumber;
    private SubscriptionModel subscription;

    public UserRegistrationModel(RegistrationDataModel registrationDataModel){
        this.name = registrationDataModel.getName();
        this.phoneNumber = registrationDataModel.getPhoneNumber();
        this.subscription = registrationDataModel.getSubscription();
    }
}
