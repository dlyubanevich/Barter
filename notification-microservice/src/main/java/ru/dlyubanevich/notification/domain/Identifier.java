package ru.dlyubanevich.notification.domain;

import lombok.Data;

@Data
public class Identifier {

    private String offerRequestId;
    private String offerResponseId;
    private String dealId;
}
