package ru.dlyubanevich.user.models;

import lombok.Getter;
import ru.dlyubanevich.user.domain.NomenclatureOption;
import ru.dlyubanevich.user.domain.Subscription;
import ru.dlyubanevich.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserSubscription {

    private final UserShort owner;
    private final List<String> usersId;
    private final List<String> nomenclatureOptionsId;
    private final List<String> offerTypes;

    public UserSubscription(User user){
        Subscription subscription = user.getSettings().getSubscription();
        this.owner = new UserShort(user.getId(), user.getName());
        this.usersId = subscription.getUsers().stream().map(UserShort::getId).collect(Collectors.toList());
        this.nomenclatureOptionsId = subscription.getNomenclatureOptions().stream().map(NomenclatureOption::getId).collect(Collectors.toList());
        this.offerTypes = subscription.getOfferTypes();
    }

}
