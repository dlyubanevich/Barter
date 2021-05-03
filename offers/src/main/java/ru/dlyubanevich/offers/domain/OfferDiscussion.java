package ru.dlyubanevich.offers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "discussions")
public class OfferDiscussion {

    @Id
    private String id;
    private String offerId;
    private List<Message> messages;

}
