package ru.dlyubanevich.notification.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dlyubanevich.notification.domain.User;

@AllArgsConstructor
@Getter
public class DataOfferResponse {

    private final String id;
    private final String description;
    private final User user;
    private final User ownerRequest;
    private final String offerRequestId;

}
