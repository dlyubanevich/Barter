package ru.dlyubanevich.nomenclatures.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "properties")
public class OptionProperties {

    @Id
    private String id;
    private String optionId;
    private String name;

}
