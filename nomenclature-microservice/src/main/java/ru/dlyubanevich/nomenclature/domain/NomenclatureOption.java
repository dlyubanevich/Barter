package ru.dlyubanevich.nomenclature.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "options")
public class NomenclatureOption {

    @Id
    private String id;
    private String name;

}
