package ru.dlyubanevich.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {

    private String name;
    private String codedBytes;

}
