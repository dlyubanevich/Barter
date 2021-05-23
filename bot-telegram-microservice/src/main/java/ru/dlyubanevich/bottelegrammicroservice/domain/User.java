package ru.dlyubanevich.bottelegrammicroservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private long telegramId;
    private long chatId;

}
