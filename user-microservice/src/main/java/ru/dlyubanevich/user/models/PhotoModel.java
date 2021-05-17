package ru.dlyubanevich.user.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
@Data
public class PhotoModel {

    private String ownerId;
    private List<FileModel> files;

    public PhotoModel(String userId, FileModel fileModel){
        this.ownerId = userId;
        this.files = Collections.singletonList(fileModel);
    }
}
