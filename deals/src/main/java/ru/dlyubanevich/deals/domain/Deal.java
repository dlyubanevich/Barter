package ru.dlyubanevich.deals.domain;

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
    private LocalDateTime dateTime;
    private long number;
    private Location location;
    private User initiator;
    private User partner;
    private String offerRequestId;
    private String offerResponseId;
    private Status status;

}
