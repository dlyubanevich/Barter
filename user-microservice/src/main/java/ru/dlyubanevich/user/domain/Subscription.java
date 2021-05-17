package ru.dlyubanevich.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlyubanevich.user.models.UserModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    private List<UserModel> users;
    private List<NomenclatureOption> nomenclatureOptions;
    private List<String> offerTypes;

}
