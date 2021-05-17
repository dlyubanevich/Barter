package ru.dlyubanevich.deal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "deals")
public class Deal {

    @Id
    private String id;
    private int number;
    private LocalDateTime dateTime;
    private Location location;
    private User initiator;
    private User partner;
    private OfferRequest offerRequest;
    private OfferResponse offerResponse;

}
