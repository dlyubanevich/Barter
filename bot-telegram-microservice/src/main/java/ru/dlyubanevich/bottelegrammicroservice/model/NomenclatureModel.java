package ru.dlyubanevich.bottelegrammicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NomenclatureModel {

    private String name;
    private UserModel owner;
    private NomenclatureOptionModel option;
    private String type;
    private String description;
    private List<FileModel> photos;

}
