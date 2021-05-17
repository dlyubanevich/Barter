package ru.dlyubanevich.nomenclature.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotoModel {

    private String ownerId;
    private List<FileModel> files;

}
