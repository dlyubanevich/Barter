package ru.dlyubanevich.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoModel {

    private String ownerId;
    private List<FileModel> files;

}
