package ru.dlyubanevich.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String gender;
    private String avatar;
    private UserDetails details;
    private UserSettings settings;
    private List<Nomenclature> items;

}
