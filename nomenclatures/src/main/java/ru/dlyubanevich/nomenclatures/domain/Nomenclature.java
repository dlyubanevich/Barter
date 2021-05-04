package ru.dlyubanevich.nomenclatures.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "nomenclatures")
public class Nomenclature {

    @Id
    private String id;
    private String name;
    private User owner;
    private String type;
    private NomenclatureOption option;
    private List<NomenclatureProperty> properties;
    private List<String> photos;
    private String description;
    private double rating;

}
