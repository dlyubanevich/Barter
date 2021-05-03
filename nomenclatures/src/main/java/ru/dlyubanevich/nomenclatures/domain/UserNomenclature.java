package ru.dlyubanevich.users.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "nomenclatures")
public class UserNomenclature {

    private String userId;
    private String nomenclatureId;


}
