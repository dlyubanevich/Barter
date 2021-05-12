package ru.dlyubanevich.notification.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subscriptions")
public class Subscription {

    private String id;
    private User owner;
    private List<String> usersId;
    private List<String> nomenclatureOptionsId;
    private List<String> offerTypes;

}
