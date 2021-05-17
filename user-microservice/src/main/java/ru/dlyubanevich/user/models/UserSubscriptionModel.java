package ru.dlyubanevich.user.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.dlyubanevich.user.domain.NomenclatureOption;
import ru.dlyubanevich.user.domain.Subscription;
import ru.dlyubanevich.user.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@Getter
public class UserSubscriptionModel {

    private final UserModel owner;
    private final List<String> usersId;
    private final List<String> nomenclatureOptionsId;
    private final List<String> offerTypes;

    public UserSubscriptionModel(User user){
        Subscription subscription = user.getSettings().getSubscription();
        this.owner = new UserModel(user.getId(), user.getName());
        if (subscription.getUsers() != null) {
            this.usersId = subscription.getUsers().stream()
                    .map(UserModel::getId)
                    .collect(Collectors.toList());
        }else {
            this.usersId = new ArrayList<>();
        }
        if (subscription.getNomenclatureOptions() != null) {
            this.nomenclatureOptionsId = subscription.getNomenclatureOptions().stream()
                    .map(NomenclatureOption::getId)
                    .distinct()
                    .collect(Collectors.toList());
        }else{
            this.nomenclatureOptionsId = new ArrayList<>();
        }
        if (subscription.getOfferTypes() != null){
            this.offerTypes = subscription.getOfferTypes();
        }else {
            this.offerTypes = new ArrayList<>();
        }

    }

}
