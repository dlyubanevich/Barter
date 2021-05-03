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
@Document(collection = "statuses")
public class DealStatus {

    @Id
    private String id;
    private String dealId;
    private Status status;
    private LocalDateTime dateTime;

}
