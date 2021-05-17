package ru.dlyubanevich.deal.model;

import lombok.Getter;
import ru.dlyubanevich.deal.domain.Deal;
import ru.dlyubanevich.deal.domain.User;

@Getter
public class DealNotification {

    private final String id;
    private final User initiator;
    private final User partner;

    public DealNotification(Deal deal){
        this.id = deal.getId();
        this.initiator = deal.getInitiator();
        this.partner = deal.getPartner();
    }
}
